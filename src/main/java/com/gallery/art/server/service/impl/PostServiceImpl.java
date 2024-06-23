package com.gallery.art.server.service.impl;

import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.db.entity.saved.SavedPostEntity;
import com.gallery.art.server.db.entity.saved.SavedPostId;
import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.dto.post.EditPost;
import com.gallery.art.server.dto.post.Post;
import com.gallery.art.server.enums.Statuses;
import com.gallery.art.server.exeption.ObjectNotExistsException;
import com.gallery.art.server.filters.PostSearch;
import com.gallery.art.server.filters.post.PostFilter;
import com.gallery.art.server.mapper.PostMapper;
import com.gallery.art.server.repository.PostRepository;
import com.gallery.art.server.repository.saved.SavedPostRepository;
import com.gallery.art.server.service.IAuthService;
import com.gallery.art.server.service.IImageService;
import com.gallery.art.server.service.IPostService;
import com.gallery.art.server.service.ITagService;
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

import static com.gallery.art.server.utils.PostSpecificationUtils.postEntitySpecificationForFilter;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {

    private final PostRepository postRepository;
    private final SavedPostRepository savedPostRepository;

    private final ITagService tagService;
    private final IImageService imageService;
    private final IAuthService authService;

    private final PostMapper postMapper;

    @Override
    public PostEntity findPostEntityById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new  ObjectNotExistsException(
                ObjectNotExistsException.ObjectType.post,
                MessageFormat.format("Пост с id {0} не найден", postId),
                postId
        ));
    }

    @Override
    public Post findPostById(Long postId) {
        return postMapper.toDto(findPostEntityById(postId));
    }

    @Override
    public Post createPost(EditPost editPost) {
        PostEntity postEntity = postMapper.asEntity(editPost, authService.getLoggedUserEntity());
        postEntity.setImages(editPost.getImages().stream().map(i -> imageService.findImageEntityById(i.getId())).collect(Collectors.toSet()));
        postEntity.setTags(editPost.getTags().stream().map(i -> tagService.findTagEntityById(i.getId())).collect(Collectors.toSet()));
        return postMapper.toDto(postRepository.save(postEntity));
    }

    @Override
    public Post updatePost(Long postId, EditPost editPost) {
        PostEntity postEntity = findPostEntityById(postId);
        postMapper.asEntity(postEntity, editPost);

        if (!authService.getLoggedUserEntity().getId().equals(postEntity.getOwner().getId())) {
            throw new IllegalArgumentException("пользователь не совпадвет");
        }
        postEntity.setImages(editPost.getImages().stream().map(i -> imageService.findImageEntityById(i.getId())).collect(Collectors.toSet()));
        postEntity.setTags(editPost.getTags().stream().map(i -> tagService.findTagEntityById(i.getId())).collect(Collectors.toSet()));

        return postMapper.toDto(postRepository.save(postEntity));
    }

    @Override
    public Page<Post> findAllPost(PostFilter filter) {
        PageRequest pageRequest = PageRequest.of(filter.getPageInfo().getNumber(), filter.getPageInfo().getSize());

        if (filter.getFilterPostRequest() != null && filter.getFilterPostRequest().saved() != null
                && filter.getFilterPostRequest().saved() && filter.getFilterPostRequest().userId() == null) {
            throw new IllegalArgumentException("Поиск сохраненных только по юзеру");
        }
        Page<PostEntity> pages = filter.getFilterPostRequest() == null ?
                postRepository.findAll(pageRequest) : postRepository.findAll(postEntitySpecificationForFilter(filter.getFilterPostRequest()), pageRequest);
        return new PageImpl<>(
                pages.getContent()
                        .stream()
                        .map(postMapper::toDto)
                        .toList(),
                pageRequest,
                pages.getTotalElements()
        );
    }

    //todo search
    @Override
    public Page<Post> searchPost(PostSearch filter) {
//        PageRequest pageRequest = PageRequest.of(filter.getPageInfo().getNumber(), filter.getPageInfo().getSize());
//        Page<PostEntity> pages = postRepository.findBySimilarity(filter.getSearchString(), pageRequest);
//        return new PageImpl<>(
//                pages.getContent()
//                        .stream()
//                        .map(postMapper::toDto)
//                        .toList(),
//                pageRequest,
//                pages.getTotalElements()
//        );
        return null;
    }


    @Override
    public List<StatusesById> deletePosts(List<Long> postIds){
        List<StatusesById> statuses = new ArrayList<>();
        for (Long id : postIds) {
            if (!postRepository.existsById(id)) {
                statuses.add(new StatusesById(id, Statuses.NOT_FOUND, "Не найдено"));
            } else {
                try {
                    postRepository.deleteById(id);
                    statuses.add(new StatusesById(id, Statuses.COMPLETED, ""));
                } catch (Exception exception) {
                    statuses.add(new StatusesById(id, Statuses.FAILED, exception.getMessage()));
                }
            }
        }
        return statuses;
    }

    @Override
    public Boolean addToSaved(Long postId) {
        UserEntity user = authService.getLoggedUserEntity();
        PostEntity post = findPostEntityById(postId);
        if (post.getOwner().getId().equals(user.getId())) {
            return true;
        }
        if (savedPostRepository.existsById(new SavedPostId(user.getId(), postId))) {
            savedPostRepository.deleteById(new SavedPostId(user.getId(), postId));
            return false;
        } else {
            savedPostRepository.save(new SavedPostEntity(user, post));
            return true;
        }
    }

    @Override
    public boolean savedByUser(Long postId, Long userId) {
        return savedPostRepository.existsById(new SavedPostId(userId, postId));
    }
}
