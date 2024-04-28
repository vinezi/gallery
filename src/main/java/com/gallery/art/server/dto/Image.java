package com.gallery.art.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность изображения")
public class Image extends BaseDto {

    @Schema(description = "Путь превью")
    protected String previewFilename;

    @Schema(description = "Путь к полному изображению")
    protected String fullFilename;
}
