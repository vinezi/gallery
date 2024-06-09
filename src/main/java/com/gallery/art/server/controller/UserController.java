package com.gallery.art.server.controller;

import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.dto.user.UserInfo;
import com.gallery.art.server.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Tag(name = "User API")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(summary = "Изменение изображения профиля пользователя")
    @PutMapping("append-image")
    public ShortUser appendUserImage(@RequestParam(name = "imageId", required = false) Long imageId) {
        return userService.appendUserImage(imageId);
    }

    @Operation(summary = "Получение полного профиля пользователя")
    @GetMapping("{id}")
    public UserInfo findUserById(@Positive @PathVariable("id") Long userId){
        return userService.getFullUserInfo(userId);
    }
}
