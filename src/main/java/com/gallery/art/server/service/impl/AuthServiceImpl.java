package com.gallery.art.server.service.impl;

import com.gallery.art.server.config.security.JwtProvider;
import com.gallery.art.server.config.security.principal.JwtAuthentication;
import com.gallery.art.server.config.security.principal.TokenInfo;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.auth.AuthUser;
import com.gallery.art.server.dto.auth.JwtResponse;
import com.gallery.art.server.dto.auth.Login;
import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.enums.Role;
import com.gallery.art.server.service.IAuthService;
import com.gallery.art.server.service.IMailService;
import com.gallery.art.server.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    private final JwtProvider jwtProvider;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final IMailService mailService;

    public AuthServiceImpl(JwtProvider jwtProvider, @Lazy IUserService userService, PasswordEncoder passwordEncoder, IMailService mailService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public ResponseEntity<?> auth(Login authRequest) {
        UserEntity userEntity = userService.findUserEntityByEmail(authRequest.getLogin());
        if (passwordEncoder.matches(authRequest.getPassword(), userEntity.getPassword()) && !userEntity.isDeleted()) {
            return ResponseEntity.ok(generatePairOfTokens(new AuthUser(userEntity.getId(), userEntity.getRole())));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");
        }
    }

    @Override
    public ResponseEntity<?> register(Login authRequest) {
        String code = UUID.randomUUID().toString(); //todo почта не подтвержлена? кидаем код //чекнуть что?
        userService.createNew(authRequest, code);
        return mailService.sendConfirmEmail(authRequest.getLogin(), code);
    }

    @Override
    public ResponseEntity<?> confirmEmail(String email, String code){
        UserEntity userEntity = userService.findUserEntityByEmail(email);
        if (passwordEncoder.matches(code, userEntity.getCode()) && !userEntity.isDeleted()) {
            userService.confirm(userEntity.getId());
            return ResponseEntity.ok("Почта подтверждена");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");
        }
    }

    @Override
    public ResponseEntity<?> refresh(String refreshToken) {
        if (jwtProvider.validateToken(refreshToken, true)) {
            final Claims claims = jwtProvider.getClaims(refreshToken, true);
            final AuthUser user = generateUserInfoFromRefreshToken(claims);
            user.setRole(userService.findUserEntityById(user.getId()).getRole());
            return ResponseEntity.ok(generatePairOfTokens(user));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Недействительный рефреш токен");
    }

    @Override
    public ShortUser getLoggedUser() {
        return userService.findUserById(jwtProvider.getAuthInfo().getUserId());
    }

    @Override
    public UserEntity getLoggedUserEntity() {
        return userService.findUserEntityById(jwtProvider.getAuthInfo().getUserId());
    }

    private JwtResponse generatePairOfTokens (AuthUser user) {
        final TokenInfo accessToken = jwtProvider.generateToken(user, false);
        final TokenInfo refreshToken = jwtProvider.generateToken(user, true);
        return new JwtResponse(accessToken.getToken(), accessToken.getExpireIn().toString(), refreshToken.getToken(), refreshToken.getExpireIn().toString());
    }

    private AuthUser generateUserInfoFromRefreshToken(Claims claims) {
        return new AuthUser(claims.get("userId", Long.class), Role.valueOf(claims.get("role", String.class)));
    }

}
