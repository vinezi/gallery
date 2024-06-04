package com.gallery.art.server.service.impl;

import com.gallery.art.server.repository.TagRepository;
import com.gallery.art.server.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements ITagService {

    private final TagRepository tagRepository;
}
