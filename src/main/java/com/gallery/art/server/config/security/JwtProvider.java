package com.gallery.art.server.config.security;

import com.gallery.art.server.config.security.principal.JwtAuthentication;
import com.gallery.art.server.config.security.principal.TokenInfo;
import com.gallery.art.server.dto.auth.AuthUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private final PrivateKey jwtAccessSecret;
    private final PrivateKey jwtRefreshSecret;
    @Value("${security.private-key.life-access}")
    private Duration lifeAccess;
    @Value("${security.private-key.life-refresh}")
    private Duration lifeRefresh;

    public JwtProvider(
            @Value("${security.private-key.access}") String encodedJwtAccessSecret,
            @Value("${security.private-key.refresh}") String encodedJwtRefreshSecret
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.jwtAccessSecret = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedJwtAccessSecret)));//new SecretKeySpec(Base64.getDecoder().decode(encodedJwtAccessSecret), "RSA");
        this.jwtRefreshSecret = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodedJwtRefreshSecret)));
    }

    public TokenInfo generateToken(@NonNull AuthUser user, boolean isRefresh) {
        final ZonedDateTime expirationInstant = ZonedDateTime.now().plus(isRefresh?lifeRefresh:lifeAccess);
        final Date expiration = Date.from(expirationInstant.toInstant());
        return new TokenInfo(
                Jwts.builder()
                        .setSubject("access")
                        .setExpiration(expiration)
                        .claim("userId", user.getId())
                        .claim("role", user.getRole().name())
                        .signWith(isRefresh?jwtRefreshSecret:jwtAccessSecret)
                        .compact(),
                expiration
        );
    }

    public Claims getClaims(@NonNull String token, boolean isRefresh) {
        return Jwts.parserBuilder()
                .setSigningKey(isRefresh?jwtRefreshSecret:jwtAccessSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean validateToken(@NonNull String token, boolean isRefresh) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Jwts.parserBuilder()
                    .setSigningKey(isRefresh?jwtRefreshSecret:jwtAccessSecret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
