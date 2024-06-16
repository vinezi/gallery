package com.gallery.art.server.filters.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Настройки пагинации")
public class PageInfo {

    @Schema(description = "Номер страницы")
    int number;

    @Schema(description = "Количество сущностей", example = "10")
    int size;
}