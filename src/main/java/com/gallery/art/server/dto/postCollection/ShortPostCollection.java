package com.gallery.art.server.dto.postCollection;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.dto.post.ShortPost;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сокращенная коллекции постов")
public class ShortPostCollection extends BaseDto {

    @Schema(description = "Название")
    private String title;

    @Schema(description = "Первый, сокращенный пост коллекции")
    private ShortPost shortPost;
}
