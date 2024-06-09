package com.gallery.art.server.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Изменение пользователя")
public class EditUser {

    @Schema(description = "Ник")
    private String name;

}
