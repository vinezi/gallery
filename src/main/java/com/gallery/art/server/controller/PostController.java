package com.gallery.art.server.controller;

import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.post.EditPost;
import com.gallery.art.server.dto.post.Post;
import com.gallery.art.server.filters.PostSearch;
import com.gallery.art.server.filters.common.PageInfo;
import com.gallery.art.server.service.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Tag(name = "Post API")
@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;

    @Operation(summary = "Получение пагинированного списка всех тегов")
    @PostMapping("action/search-all")
    public Page<Post> findAllPost(@Valid @RequestBody PageInfo pageInfo){
        return postService.findAllPost(pageInfo);
    }

    @Operation(summary = "Поиск тегов (НЕ РЕАЛИЗОВАНО)")
    @PostMapping("action/search-by-filter")
    public Page<Post> searchPost(@Valid @RequestBody PostSearch filter){
        return postService.searchPost(filter);
    }

    @Operation(summary = "Получение поста")
    @GetMapping("{id}")
    public Post findPostById(@Positive @PathVariable("id") Long postId){
        return postService.findPostById(postId);
    }

    @Operation(summary = "Создание поста")
    @PostMapping()
    public Post createPost(@Valid @RequestBody EditPost post){
        return postService.createPost(post);
    }

    @Operation(summary = "Обновление поста")
    @PutMapping("{id}")
    public Post updatePost(@Positive @PathVariable("id") Long postId, @Valid @RequestBody EditPost post){
        return postService.updatePost(postId, post);
    }

    @Operation(summary = "Удаление постов")
    @DeleteMapping()
    public List<StatusesById> deletePosts(@RequestParam List<Long> postsIds){
        return postService.deletePosts(postsIds);
    }
}
