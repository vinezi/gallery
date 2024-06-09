package com.gallery.art.server.dto.postCollection;

import com.gallery.art.server.dto.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Schema(description = "Изменение коллекции постов")
public class EditPostCollection {

    @Schema(description = "Название")
    private String title;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Посты")
    private Set<Post> posts = new HashSet<>();
}
