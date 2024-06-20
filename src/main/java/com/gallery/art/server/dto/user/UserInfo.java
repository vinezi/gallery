package com.gallery.art.server.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Пользователь")
public class UserInfo extends ShortUser {

    @Schema(description = "Описание")
    private String description;

}
