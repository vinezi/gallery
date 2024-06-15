package com.gallery.art.server.mapper;

import com.gallery.art.server.config.EntityMapperConfig;
import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.dto.postCollection.PostCollection;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfig.class)
public interface PostCollectionMapper {

    PostCollection toDto(PostCollectionEntity source);
}
