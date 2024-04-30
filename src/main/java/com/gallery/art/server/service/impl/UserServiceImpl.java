package com.gallery.art.server.service.impl;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.repository.UserRepository;
import com.gallery.art.server.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.MessageFormat;

import static com.gallery.art.server.utils.ValidationUtils.validateEmail;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity findUserEntityByEmail(String email) {
        if (!validateEmail(email))
            throw new IllegalArgumentException("Не является почтой");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Пользователь с почтой {0} не найден", email)));
    }
}
