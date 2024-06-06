package com.gallery.art.server.service.impl;

import com.gallery.art.server.db.entity.TagEntity;
import com.gallery.art.server.dto.tag.EditTag;
import com.gallery.art.server.dto.tag.Tag;
import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.enums.Statuses;
import com.gallery.art.server.filters.TagSearch;
import com.gallery.art.server.filters.common.PageInfo;
import com.gallery.art.server.mapper.TagMapper;
import com.gallery.art.server.repository.TagRepository;
import com.gallery.art.server.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements ITagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Tag saveTag(EditTag editTag){
        TagEntity tagEntity = tagMapper.asEntity(editTag);
        return tagMapper.toDto(tagRepository.save(tagEntity));
    }

    @Override
    public Tag updateTag(Long tagId, EditTag tag){
        existTafEntityById(tagId);
        TagEntity tagEntity = tagMapper.asEntity(tag, tagId);
        return tagMapper.toDto(tagRepository.save(tagEntity));
    }

    @Override
    public TagEntity findTagEntityById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Тэг с id {0} не найден", tagId)));
    }

    @Override
    public Tag findTagById(Long tagId) {
        return tagMapper.toDto(findTagEntityById(tagId));
    }

    @Override
    public Page<Tag> findAllTag(PageInfo pageInfo) {
        PageRequest pageRequest = PageRequest.of(pageInfo.getNumber(), pageInfo.getSize());
        Page<TagEntity> pages = tagRepository.findAll(pageRequest);
        return new PageImpl<>(
                pages.getContent()
                        .stream()
                        .map(tagMapper::toDto)
                        .toList(),
                pageRequest,
                pages.getTotalElements()
        );
    }

    @Override
    public Page<Tag> searchTag(TagSearch filter) {
        PageRequest pageRequest = PageRequest.of(filter.getPageInfo().getNumber(), filter.getPageInfo().getSize());
        Page<TagEntity> pages = tagRepository.findBySimilarity(filter.getSearchString(), pageRequest);
        return new PageImpl<>(
                pages.getContent()
                        .stream()
                        .map(tagMapper::toDto)
                        .toList(),
                pageRequest,
                pages.getTotalElements()
        );
    }

    @Override
    public List<StatusesById> deleteTags(List<Long> tagIds){
        List<StatusesById> statuses = new ArrayList<>();
        for (Long id : tagIds) {
            if (!tagRepository.existsById(id)) {
                statuses.add(new StatusesById(id, Statuses.NOT_FOUND, "Не найдено"));
            } else {
                try {
                    tagRepository.deleteById(id);
                    statuses.add(new StatusesById(id, Statuses.COMPLETED, ""));
                } catch (Exception exception) {
                    statuses.add(new StatusesById(id, Statuses.FAILED, exception.getMessage()));
                }
            }
        }
        return statuses;
    }

    public void existTafEntityById(Long tagId) {
        if (!tagRepository.existsById(tagId))
            throw  new EntityNotFoundException(MessageFormat.format("Тэг с id {0} не найден", tagId));
    }

    //todo v name validate
//    public void existTafEntityByName(String name) {
//        if (!tagRepository) {
//
//        }
//    }
    
    
}
