package com.gallery.art.server.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Изменение пользователя")
public class EditUser {

    @NotBlank(message = "не должно быть пустым")
    @Schema(description = "Ник")
    private String name;

    @Schema(description = "Идентификатор картинки")
    private Long imageId;

    @Schema(description = "Описание")
    private String description;

}
