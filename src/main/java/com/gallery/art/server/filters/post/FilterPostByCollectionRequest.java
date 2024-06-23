package com.gallery.art.server.filters.post;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "Фильтр постов внутри коллекции")
public record FilterPostByCollectionRequest(
        @Schema(description = "Идентификатор коллекции")
        @NotNull Long collectionId) {

}
