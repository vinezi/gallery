package com.gallery.art.server.controller;

import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
