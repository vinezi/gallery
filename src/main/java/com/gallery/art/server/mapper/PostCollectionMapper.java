package com.gallery.art.server.mapper;

import com.gallery.art.server.config.EntityMapperConfig;
import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.postCollection.EditPostCollection;
import com.gallery.art.server.dto.postCollection.PostCollection;
import com.gallery.art.server.dto.postCollection.ShortPostCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = EntityMapperConfig.class, uses = PostMapper.class)
public interface PostCollectionMapper {

    PostCollection toDto(PostCollectionEntity source);

    @Mapping(target = "shortPosts", source = "posts")
    ShortPostCollection toShortDto(PostCollectionEntity source);

    @Mapping(target = "saved", ignore = true)
    @Mapping(target = "savedByUser", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "owner", source = "userEntity")
    @Mapping(target = "description", source = "postCollection.description")
    PostCollectionEntity asEntity(EditPostCollection postCollection, UserEntity userEntity);

    @Mapping(target = "saved", ignore = true)
    @Mapping(target = "savedByUser", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "description", source = "source.description")
    void asEntity(@MappingTarget PostCollectionEntity postCollection, EditPostCollection source);
}
