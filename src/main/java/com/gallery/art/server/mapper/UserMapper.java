package com.gallery.art.server.mapper;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toDto(UserEntity src); 
}
