package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.ImageEntity;
import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.dto.post.EditPost;
import com.gallery.art.server.dto.post.Post;
import com.gallery.art.server.dto.post.ShortPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Mapper(uses = { ImageMapper.class, TagMapper.class})
public interface PostMapper {

    Post toDto(PostEntity source);

    @Mapping(target = "image", source = "images", qualifiedByName = "toImageShort")
    ShortPost toShortDto(PostEntity source);

    @Mapping(target = "savedByUser", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "description", source = "source.description")
    PostEntity asEntity(EditPost source, UserEntity owner);

    @Mapping(target = "savedByUser", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    void asEntity(@MappingTarget PostEntity post, EditPost source);

    @Named("toImageShort")
    default Image toImageShort(Set<ImageEntity> imageEntities) {
        if (CollectionUtils.isEmpty(imageEntities))
            return null;
        ImageEntity imageEntity = imageEntities.iterator().next();
        return new Image(imageEntity.getId(), imageEntity.getPreviewFilename(), imageEntity.getFullFilename());
    }
}
