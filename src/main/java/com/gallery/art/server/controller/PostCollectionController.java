package com.gallery.art.server.controller;

import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.postCollection.EditPostCollection;
import com.gallery.art.server.dto.postCollection.PostCollection;
import com.gallery.art.server.filters.common.PageInfo;
import com.gallery.art.server.service.IPostCollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Tag(name = "Post Collection API")
@RestController
@RequestMapping("post-collection")
@RequiredArgsConstructor
public class PostCollectionController {

    private final IPostCollectionService postCollectionService;

    @Operation(summary = "Получение пагинированного списка всех коллекций постов")
    @PostMapping("action/search-all")
    public Page<PostCollection> findAllPostCollection(@Valid @RequestBody PageInfo pageInfo){
        return postCollectionService.findAllPostCollection(pageInfo);
    }

    @Operation(summary = "Получение коллекции постов")
    @GetMapping("{id}")
    public PostCollection findPostCollectionById(@Positive @PathVariable("id") Long postId){
        return postCollectionService.findPostCollectionById(postId);
    }

    @Operation(summary = "Создание коллекции поста")
    @PostMapping()
    public PostCollection createPostCollection(@Valid @RequestBody EditPostCollection postCollection){
        return postCollectionService.createPostCollection(postCollection);
    }

    @Operation(summary = "Обновление коллекции постов")
    @PutMapping("{id}")
    public PostCollection updatePostCollection(@Positive @PathVariable("id") Long postId, @Valid @RequestBody EditPostCollection postCollection){
        return postCollectionService.updatePost(postId, postCollection);
    }

    @Operation(summary = "Удаление коллекции постов")
    @DeleteMapping()
    public List<StatusesById> deletePostCollections(@RequestParam List<Long> postsIds){
        return postCollectionService.deletePostCollections(postsIds);
    }
}
