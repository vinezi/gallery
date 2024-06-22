package com.gallery.art.server.dto.post;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.dto.tag.Tag;
import com.gallery.art.server.dto.user.ShortUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Schema(description = "Пост")
public class Post extends BaseDto {

    @Schema(description = "Название поста")
    private String title;

    @Schema(description = "Описание поста")
    private String description;

    @Schema(description = "Автор поста")
    private ShortUser owner;

    @Schema(description = "Тэги")
    private Set<Tag> tags = new HashSet<>();

    @Schema(description = "Изображения")
    private Set<Image> images = new HashSet<>();

    @Schema(description = "Дата создания")
    private OffsetDateTime createdDate;

    @Schema(description = "Сохранен у пользователя")
    private boolean saved;

}
