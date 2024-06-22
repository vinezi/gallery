package com.gallery.art.server.service.impl;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.db.entity.saved.SavedCollectionEntity;
import com.gallery.art.server.db.entity.saved.SavedCollectionId;
import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.postCollection.EditPostCollection;
import com.gallery.art.server.dto.postCollection.PostCollection;
import com.gallery.art.server.enums.Statuses;
import com.gallery.art.server.exeption.ObjectNotExistsException;
import com.gallery.art.server.filters.post.PostFilter;
import com.gallery.art.server.mapper.PostCollectionMapper;
import com.gallery.art.server.repository.PostCollectionRepository;
import com.gallery.art.server.repository.saved.SavedPostCollectionRepository;
import com.gallery.art.server.service.IAuthService;
import com.gallery.art.server.service.IPostCollectionService;
import com.gallery.art.server.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gallery.art.server.utils.PostSpecificationUtils.postCollectionEntitySpecificationForFilter;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCollectionServiceImpl implements IPostCollectionService {

    private final PostCollectionRepository postCollectionRepository;
    private final SavedPostCollectionRepository savedCollectionRepository;

    private final IPostService postService;
    private final IAuthService authService;

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

    @Override
    public PostCollection createPostCollection(EditPostCollection editPostCollection) {
        PostCollectionEntity postCollectionEntity = postCollectionMapper.asEntity(editPostCollection, authService.getLoggedUserEntity());
        postCollectionEntity.setPosts(editPostCollection.getPosts().stream().map(p -> postService.findPostEntityById(p.getId())).collect(Collectors.toSet()));
        return postCollectionMapper.toDto(postCollectionRepository.save(postCollectionEntity));
    }

    @Override
    public PostCollection updatePost(Long postCollectionId, EditPostCollection editPostCollection) {
        PostCollectionEntity postCollectionEntity = findPostCollectionEntityById(postCollectionId);
        postCollectionMapper.asEntity(postCollectionEntity, editPostCollection);

        if (!authService.getLoggedUserEntity().getId().equals(postCollectionEntity.getOwner().getId())) {
            throw new IllegalArgumentException("пользователь не совпадвет");
        }
        postCollectionEntity.setPosts(editPostCollection.getPosts().stream().map(p -> postService.findPostEntityById(p.getId())).collect(Collectors.toSet()));

        return postCollectionMapper.toDto(postCollectionRepository.save(postCollectionEntity));
    }

    @Override
    public Page<PostCollection> findAllPostCollection(PostFilter filter) {
        PageRequest pageRequest = PageRequest.of(filter.getPageInfo().getNumber(), filter.getPageInfo().getSize());

        if (filter.getFilterPostRequest() != null && filter.getFilterPostRequest().saved() != null
                && filter.getFilterPostRequest().saved() && filter.getFilterPostRequest().userId() == null) {
            throw new IllegalArgumentException("Поиск сохраненных только по юзеру");
        }
        Page<PostCollectionEntity> pages = filter.getFilterPostRequest() == null ?
                postCollectionRepository.findAll(pageRequest) : postCollectionRepository.findAll(postCollectionEntitySpecificationForFilter(filter.getFilterPostRequest()), pageRequest);
        return new PageImpl<>(
                pages.getContent()
                        .stream()
                        .map(postCollectionMapper::toDto)
                        .toList(),
                pageRequest,
                pages.getTotalElements()
        );
    }

    @Override
    public List<StatusesById> deletePostCollections(List<Long> postCollectionIds){
        List<StatusesById> statuses = new ArrayList<>();
        for (Long id : postCollectionIds) {
            if (!postCollectionRepository.existsById(id)) {
                statuses.add(new StatusesById(id, Statuses.NOT_FOUND, "Не найдено"));
            } else {
                try {
                    postCollectionRepository.deleteById(id);
                    statuses.add(new StatusesById(id, Statuses.COMPLETED, ""));
                } catch (Exception exception) {
                    statuses.add(new StatusesById(id, Statuses.FAILED, exception.getMessage()));
                }
            }
        }
        return statuses;
    }

    @Override
    public Boolean addToSaved(Long collectionId) {
        UserEntity user = authService.getLoggedUserEntity();
        PostCollectionEntity collection = findPostCollectionEntityById(collectionId);
        if (collection.getOwner().getId().equals(user.getId())) {
            return true;
        }
        if (savedCollectionRepository.existsById(new SavedCollectionId(user.getId(), collectionId))) {
            savedCollectionRepository.deleteById(new SavedCollectionId(user.getId(), collectionId));
            return false;
        } else {
            savedCollectionRepository.save(new SavedCollectionEntity(user, collection));
            return true;
        }
    }

    @Override
    public boolean savedByUser(Long collectionId, Long userId) {
        return savedCollectionRepository.existsById(new SavedCollectionId(userId, collectionId));
    }
}
