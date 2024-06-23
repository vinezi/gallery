package com.gallery.art.server.dto.postCollection;

import com.gallery.art.server.dto.BaseDto;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.dto.user.ShortUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(description = "Коллекции постов")
public class PostCollection extends BaseDto {

    @Schema(description = "Название")
    private String title;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Изображение из первого поста")
    private Image image;

    @Schema(description = "Владелец")
    private ShortUser owner;

    @Schema(description = "Дата создания")
    private OffsetDateTime createdDate;

    @Schema(description = "Сохранен у пользователя")
    private boolean saved;
}
