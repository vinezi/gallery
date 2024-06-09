package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.dto.user.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    ShortUser toDto(UserEntity src);

    @Mapping(target = "posts", ignore = true) //todo v fix
    @Mapping(target = "userCollection", ignore = true)
    UserInfo toFullDto(UserEntity src);
}
