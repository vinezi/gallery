package com.gallery.art.server.service.impl;

import com.gallery.art.server.db.entity.UserEntity;
import com.gallery.art.server.dto.auth.Login;
import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.enums.Role;
import com.gallery.art.server.mapper.UserMapper;
import com.gallery.art.server.repository.UserRepository;
import com.gallery.art.server.service.IAuthService;
import com.gallery.art.server.service.IImageService;
import com.gallery.art.server.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.UUID;

import static com.gallery.art.server.utils.ValidationUtils.validateEmail;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final IAuthService authService;
    private final IImageService imageService;

    @Override
    public UserEntity findUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Пользователь с id {0} не найден", id)));
    }

    @Override
    public UserEntity findUserEntityByEmail(String email) {
        if (!validateEmail(email))
            throw new IllegalArgumentException("Не является почтой");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Пользователь с почтой {0} не найден", email)));
    }

    @Override
    public boolean existUserByEmail(String email) {
        if (!validateEmail(email))
            throw new IllegalArgumentException("Не является почтой");
        return userRepository.existsByEmail(email);
    }

    @Override
    public ShortUser findUserById(Long id) {
        return userMapper.toDto(findUserEntityById(id));
    }

    @Override
    public ShortUser createNew(Login login, String code) {
        if (existUserByEmail(login.getLogin()))
            throw new IllegalArgumentException(MessageFormat.format("Пользователь с почтой {0} уже существует", login.getLogin()));
        return saveUser(new UserEntity(
                "user-" + UUID.randomUUID().toString(),
                login.getLogin(),
                false,
                passwordEncoder.encode(login.getPassword()),
                false,
                Role.USER,
                passwordEncoder.encode(code)
        ));
    }

    @Override
    public void confirm(Long id) {
        UserEntity user = findUserEntityById(id);
        user.setEmailConfirmed(true);
        userRepository.save(user);
    }

    @Override
    public ShortUser appendUserImage(Long imageId) {
        UserEntity userEntity = findUserEntityById(authService.getLoggedUserEntity().getId());
        userEntity.setImage(imageService.findImageEntityById(imageId));
        return saveUser(userEntity);
    }

    private ShortUser saveUser(UserEntity userEntity) {
        return userMapper.toDto(userRepository.save(userEntity));
    }

}
