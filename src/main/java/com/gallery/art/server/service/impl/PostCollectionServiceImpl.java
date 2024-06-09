package com.gallery.art.server.service.impl;

import com.gallery.art.server.mapper.PostCollectionMapper;
import com.gallery.art.server.repository.PostCollectionRepository;
import com.gallery.art.server.service.IPostCollectionService;
import com.gallery.art.server.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCollectionServiceImpl implements IPostCollectionService {

    private final PostCollectionRepository postCollectionRepository;

    private final IPostService postService;

    private final PostCollectionMapper postCollectionMapper;
}
