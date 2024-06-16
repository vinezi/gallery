package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.dto.user.UserInfo;
import org.mapstruct.Mapper;

@Mapper(uses = {PostMapper.class, PostCollectionMapper.class})
public interface UserMapper {

    ShortUser toDto(UserEntity src);

    UserInfo toFullDto(UserEntity src);
}
