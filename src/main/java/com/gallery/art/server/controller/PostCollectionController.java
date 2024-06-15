package com.gallery.art.server.controller;

import com.gallery.art.server.dto.postCollection.PostCollection;
import com.gallery.art.server.service.IPostCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@Tag(name = "Post Collection API")
@RestController
@RequestMapping("post-collection")
@RequiredArgsConstructor
public class PostCollectionController {

    private final IPostCollectionService postCollectionService;

    @Operation(summary = "Получение коллекции постов")
    @GetMapping("{id}")
    public PostCollection findPostById(@Positive @PathVariable("id") Long postId){
        return postCollectionService.findPostCollectionById(postId);
    }
}
