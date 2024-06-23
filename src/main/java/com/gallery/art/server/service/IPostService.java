package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.post.EditPost;
import com.gallery.art.server.dto.post.Post;
import com.gallery.art.server.filters.PostSearch;
import com.gallery.art.server.filters.post.PostByCollectionFilter;
import com.gallery.art.server.filters.post.PostFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPostService {
    PostEntity findPostEntityById(Long lotId);

    Post findPostById(Long postId);

    Post createPost(EditPost editPost);

    Post updatePost(Long postId, EditPost editPost);

    Page<Post> findAllPost(PostFilter pageInfo);

    Page<Post> findPostByCollection(PostByCollectionFilter filter);

    Page<Post> searchPost(PostSearch filter);

    List<StatusesById> deletePosts(List<Long> postIds);

    Boolean addToSaved(Long postId);

    boolean savedByUser(Long postId, Long userId);
}
