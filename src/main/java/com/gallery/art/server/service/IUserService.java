package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.UserEntity;

public interface IUserService {
    UserEntity findUserEntityByEmail(String email);
}
