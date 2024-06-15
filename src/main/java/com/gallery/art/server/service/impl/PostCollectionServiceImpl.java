package com.gallery.art.server.service.impl;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.dto.postCollection.PostCollection;
import com.gallery.art.server.exeption.ObjectNotExistsException;
import com.gallery.art.server.mapper.PostCollectionMapper;
import com.gallery.art.server.repository.PostCollectionRepository;
import com.gallery.art.server.service.IPostCollectionService;
import com.gallery.art.server.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCollectionServiceImpl implements IPostCollectionService {

    private final PostCollectionRepository postCollectionRepository;

    private final IPostService postService;

    private final PostCollectionMapper postCollectionMapper;

    @Override
    public PostCollectionEntity findPostCollectionEntityById(Long postCollectionId) {
        return postCollectionRepository.findById(postCollectionId).orElseThrow(() -> new ObjectNotExistsException(
                ObjectNotExistsException.ObjectType.postCollection,
                MessageFormat.format("Коллекция с id {0} не найдена", postCollectionId),
                postCollectionId
        ));
    }

    @Override
    public PostCollection findPostCollectionById(Long postCollectionId) {
        return postCollectionMapper.toDto(findPostCollectionEntityById(postCollectionId));
    }



}
