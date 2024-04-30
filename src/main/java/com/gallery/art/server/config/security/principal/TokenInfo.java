package com.gallery.art.server.config.security.principal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TokenInfo {
    private String token;
    private Date expireIn;
}
