package com.gallery.art.server.dto.user;

import com.gallery.art.server.dto.post.Post;
import com.gallery.art.server.dto.postCollection.PostCollection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Schema(description = "Пользователь")
public class UserInfo extends ShortUser {

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Посты пользователя")
    private Set<Post> posts = new HashSet<>(); //todo fix to short

    @Schema(description = "Коллекции пользователя")
    private Set<PostCollection> userCollection = new HashSet<>(); //todo fix to short

}
