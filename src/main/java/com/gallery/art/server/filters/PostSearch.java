package com.gallery.art.server.filters;

import com.gallery.art.server.filters.common.BaseFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema(description = "Поиск поста")
public class PostSearch extends BaseFilter {

    @NotBlank
    private String searchString;
}
