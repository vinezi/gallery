package com.gallery.art.server.db.entity.saved;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class SavedCollectionId implements Serializable {
    private Long user;
    private Long postCollection;
}
