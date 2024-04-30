package com.gallery.art.server.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    @Schema(example = "wewewe.ww52@gmail.com")
    private String login;
    private String password;
}
