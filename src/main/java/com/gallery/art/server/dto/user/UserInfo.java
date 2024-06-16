package com.gallery.art.server.dto.user;

import com.gallery.art.server.dto.post.ShortPost;
import com.gallery.art.server.dto.postCollection.ShortPostCollection;
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
    private Set<ShortPost> posts = new HashSet<>();

    @Schema(description = "Коллекции пользователя")
    private Set<ShortPostCollection> userCollection = new HashSet<>();

    @Schema(description = "Сохраненные посты пользователя")
    private Set<ShortPost> savedPosts = new HashSet<>();

    @Schema(description = "Сохраненные коллекции пользователя")
    private Set<ShortPostCollection> savedCollections = new HashSet<>();

}
