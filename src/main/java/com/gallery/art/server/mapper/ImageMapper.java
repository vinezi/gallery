package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.ImageEntity;
import com.gallery.art.server.dto.Image;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper {

    Image toDto(ImageEntity src);
}
