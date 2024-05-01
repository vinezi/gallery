package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.auth.Login;
import com.gallery.art.server.dto.user.User;

public interface IUserService {
    UserEntity findUserEntityById(Long id);

    UserEntity findUserEntityByEmail(String email);

    boolean existUserByEmail(String email);

    User findUserById(Long id);

    User createNew(Login login, String code);

    void confirm(Long id);
}
