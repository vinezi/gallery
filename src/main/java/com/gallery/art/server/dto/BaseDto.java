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
@Schema(description = "Базовая сущность")
public class BaseDto {
    @Schema(description = "Идентификатор сущности")
    protected Long id;
}
