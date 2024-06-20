package com.gallery.art.server.filters.post;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;

@Schema(description = "Фильтр постов")
public record FilterPostRequest(
        @Schema(description = "Сохраненый")
        @Nullable Boolean saved,

        @Schema(description = "Идентификатор пользователя")
        @Nullable Long userId) {
}
