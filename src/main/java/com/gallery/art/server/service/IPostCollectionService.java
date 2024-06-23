package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.postCollection.EditPostCollection;
import com.gallery.art.server.dto.postCollection.PostCollection;
import com.gallery.art.server.filters.post.PostFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPostCollectionService {
    PostCollectionEntity findPostCollectionEntityById(Long postCollectionId);

    PostCollection findPostCollectionById(Long postCollectionId);

    PostCollection createPostCollection(EditPostCollection editPostCollection);

    PostCollection updatePostCollection(Long postCollectionId, EditPostCollection editPostCollection);

    Page<PostCollection> findAllPostCollection(PostFilter filter);

    List<StatusesById> deletePostCollections(List<Long> postCollectionIds);

    Boolean addToSaved(Long collectionId);

    boolean savedByUser(Long collectionId, Long userId);

    void savePostToCollection(Long collectionId, Long postId, boolean delete);
}
