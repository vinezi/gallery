package com.gallery.art.server.db.entity.collection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PostInCollectionId implements Serializable {
    private Long post;
    private Long collection;
}
