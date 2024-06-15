package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.dto.postCollection.PostCollection;

public interface IPostCollectionService {
    PostCollectionEntity findPostCollectionEntityById(Long postCollectionId);

    PostCollection findPostCollectionById(Long postCollectionId);
}
