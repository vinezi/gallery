package com.gallery.art.server.db.listener;

import com.gallery.art.server.config.security.principal.JwtAuthentication;
import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.service.IPostCollectionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;

@Component
public class PostCollectionEntityListener {

    private final IPostCollectionService postCollectionService;

    public PostCollectionEntityListener(@Lazy IPostCollectionService postCollectionService) {
        this.postCollectionService = postCollectionService;
    }

    @PostLoad
    public void checkSaved(PostCollectionEntity collectionEntity) {
        boolean saved = false;
        if (SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthentication jwtAuth) {
            saved = postCollectionService.savedByUser(collectionEntity.getId(), jwtAuth.getUserId());
        }
        collectionEntity.setSaved(saved);
    }
}
