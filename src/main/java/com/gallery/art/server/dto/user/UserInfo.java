package com.gallery.art.server.dto.user;

import com.gallery.art.server.db.entity.PostCollection;
import com.gallery.art.server.dto.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Schema(description = "Пользователь")
public class UserInfo extends ShortUser {

    @Schema(description = "Посты пользователя")
    private Set<Post> posts = new HashSet<>();

    @Schema(description = "Коллекции пользователя")
    private Set<PostCollection> userCollection = new HashSet<>();

    //todo v
}
