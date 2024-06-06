package com.gallery.art.server.filters.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseFilter {

    @Schema(description = "Настройки пагинации")
    @NotNull(message = "Не должно быть нулевым")
    protected PageInfo pageInfo;
}
