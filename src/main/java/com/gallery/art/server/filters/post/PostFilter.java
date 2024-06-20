package com.gallery.art.server.filters.post;

import com.gallery.art.server.filters.common.BaseFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@Schema(description = "Фильтрация постов")
public class PostFilter extends BaseFilter {
    @Valid FilterPostRequest filterPostRequest;
}
