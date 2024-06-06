package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.TagEntity;
import com.gallery.art.server.dto.tag.EditTag;
import com.gallery.art.server.dto.tag.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TagMapper {

    Tag toDto(TagEntity src);

    @Mapping(target = "id", ignore = true)
    TagEntity asEntity(EditTag src);

    TagEntity asEntity(EditTag src, Long id);
}
