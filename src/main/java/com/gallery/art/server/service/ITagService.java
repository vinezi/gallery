package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.TagEntity;
import com.gallery.art.server.dto.tag.EditTag;
import com.gallery.art.server.dto.tag.Tag;
import com.gallery.art.server.dto.common.StatusesById;
import com.gallery.art.server.filters.TagSearch;
import com.gallery.art.server.filters.common.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITagService {

    Tag saveTag(EditTag editTag);

    Tag updateTag(Long tagId, EditTag tag);

    TagEntity findTagEntityById(Long tagId);

    Tag findTagById(Long tagId);

    Page<Tag> findAllTag(PageInfo pageInfo);

    Page<Tag> searchTag(TagSearch filter);

    List<StatusesById> deleteTags(List<Long> tagIds);
}
