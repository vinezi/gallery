package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.user.ShortUser;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    ShortUser toDto(UserEntity src);
}
