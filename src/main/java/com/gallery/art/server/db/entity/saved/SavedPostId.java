package com.gallery.art.server.db.entity.saved;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class SavedPostId implements Serializable {
    private Long user;
    private Long post;
}
