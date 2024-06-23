package com.gallery.art.server.dto.postCollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Изменение коллекции постов")
public class EditPostCollection {

    @NotBlank(message = "не может быть пустым")
    @Schema(description = "Название")
    private String title;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Скытый", example = "false")
    private boolean hide = false;
}
