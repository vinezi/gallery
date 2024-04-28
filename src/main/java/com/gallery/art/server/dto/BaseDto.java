package com.gallery.art.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Базовая сущность")
public class BaseDto {
    @Schema(description = "Идентификатор сущности")
    protected Long id;
}
