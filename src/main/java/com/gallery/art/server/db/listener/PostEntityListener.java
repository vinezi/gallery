package com.gallery.art.server.db.listener;

import com.gallery.art.server.config.security.principal.JwtAuthentication;
import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.service.IPostService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;

@Component
public class PostEntityListener {

    private final IPostService postService;

    public PostEntityListener(@Lazy IPostService postService) {
        this.postService = postService;
    }

    @PostLoad
    public void checkSaved(PostEntity post) {
        boolean saved = false;
        if (SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthentication jwtAuth) {
            saved = postService.savedByUser(post.getId(), jwtAuth.getUserId());
        }
        post.setSaved(saved);
    }
}
