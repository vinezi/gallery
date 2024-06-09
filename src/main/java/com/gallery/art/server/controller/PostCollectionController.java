package com.gallery.art.server.controller;

import com.gallery.art.server.service.IPostCollectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PostCollection API")
@RestController
@RequestMapping("post-collection")
@RequiredArgsConstructor
public class PostCollectionController {

    private final IPostCollectionService postCollectionService;


}
