package com.gallery.art.server.dto.post;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.dto.Image;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сокращенный пост")
public class ShortPost extends BaseDto {

    private Image image;
}
