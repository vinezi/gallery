package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.post.EditPost;
import com.gallery.art.server.dto.post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = { ImageMapper.class, TagMapper.class})
public interface PostMapper {

    Post toDto(PostEntity source);

    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    PostEntity asEntity(EditPost source, UserEntity owner);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    void asEntity(@MappingTarget PostEntity post, EditPost source);

}
