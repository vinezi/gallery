package com.gallery.art.server.mapper;

import com.gallery.art.server.config.EntityMapperConfig;
import com.gallery.art.server.db.entity.ImageEntity;
import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.db.entity.collection.PostInCollectionEntity;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.dto.postCollection.EditPostCollection;
import com.gallery.art.server.dto.postCollection.PostCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Mapper(config = EntityMapperConfig.class)
public interface PostCollectionMapper {

    @Mapping(target = "image", source = "posts", qualifiedByName = "toImageShort")
    PostCollection toDto(PostCollectionEntity source);

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

    @Named("toImageShort")
    default Image toImageShort(Set<PostInCollectionEntity> posts) {
        if (CollectionUtils.isEmpty(posts))
            return null;
        ImageEntity imageEntity = posts.iterator().next().getPost().getImages().iterator().next();
        return new Image(imageEntity.getId(), imageEntity.getPreviewFilename(), imageEntity.getFullFilename());
    }
}
