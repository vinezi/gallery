package com.gallery.art.server.controller;

import com.gallery.art.server.dto.auth.Login;
import com.gallery.art.server.dto.user.ShortUser;
import com.gallery.art.server.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Auth API")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("reg")
    public ResponseEntity<?> signIn(@RequestBody @Valid Login authRequest) {
        return authService.register(authRequest);
    }
    @Operation(summary = "Подтверждение почты")
    @GetMapping("confirm")
    public ResponseEntity<?> confirm(@RequestParam("email") String email, @RequestParam("code") String code) {
        return authService.confirmEmail(email, code);
    }

    @Operation(summary = "Авторизация")
    @PostMapping("login")
    public ResponseEntity<?> auth(@RequestBody @Valid Login authRequest) {
        return authService.auth(authRequest);
    }

    @Operation(summary = "Получение новой пары токенов")
    @PostMapping("refresh")
    public ResponseEntity<?> getNewPairOfTokensByRefreshToken(@RequestParam String refreshToken) {
        return authService.refresh(refreshToken);
    }

    @Operation(summary = "Получение данных авторизированного пользователя")
    @GetMapping("logged-user")
    public ShortUser getLoggedUser() {
        return authService.getLoggedUser();
    }
}
