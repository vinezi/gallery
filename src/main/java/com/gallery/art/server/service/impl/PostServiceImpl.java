package com.gallery.art.server.service.impl;

import com.gallery.art.server.repository.PostRepository;
import com.gallery.art.server.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

    private final PostRepository postRepository;
}
