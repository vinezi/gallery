package com.gallery.art.server.filters.post;

import com.gallery.art.server.filters.common.BaseFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Фильтрация постов в коллекции")
public class PostByCollectionFilter extends BaseFilter {
    @Valid @NotNull FilterPostByCollectionRequest filterPostByCollectionRequest;
}
