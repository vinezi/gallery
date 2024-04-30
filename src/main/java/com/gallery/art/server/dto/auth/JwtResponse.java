package com.gallery.art.server.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String accessExpiresIn;
    private String refreshToken;
    private String refreshExpiresIn;
}
