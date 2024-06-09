package com.gallery.art.server.dto.post;

import com.gallery.art.server.dto.Image;
import com.gallery.art.server.dto.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Schema(description = "Измиинение поста")
public class EditPost {

    @NotBlank(message = "не может быть пустым")
    @Schema(description = "Название поста")
    private String title;

    @Schema(description = "Описание поста")
    private String description;

    @Schema(description = "Тэги")
    private Set<Tag> tags = new HashSet<>();

    @Size(max = 3, min = 1, message = "от 1 до 3")
    @Schema(description = "Изображения")
    private Set<Image> images = new HashSet<>();
}
