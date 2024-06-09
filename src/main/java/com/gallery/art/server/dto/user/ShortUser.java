package com.gallery.art.server.dto.user;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сокращенный пользователь")
public class ShortUser extends BaseDto {

    @Schema(description = "Ник")
    private String name;

    @Schema(description = "Почта")
    private String email;

    @Schema(description = "Аватарка")
    private Image image;

    @Schema(description = "Роль")
    private Role role;
}
