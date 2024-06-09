package com.gallery.art.server.service;

import com.gallery.art.server.config.security.principal.JwtAuthentication;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.auth.Login;
import com.gallery.art.server.dto.user.ShortUser;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    JwtAuthentication getAuthInfo();

    ResponseEntity<?> auth(Login authRequest);

    ResponseEntity<?> register(Login authRequest);

    ResponseEntity<?> confirmEmail(String email, String code);

    ResponseEntity<?> refresh(String refreshToken);

    ShortUser getLoggedUser();

    UserEntity getLoggedUserEntity();
}
