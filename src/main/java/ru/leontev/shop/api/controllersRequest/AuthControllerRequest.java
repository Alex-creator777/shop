package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.LoginRequestDto;
import ru.leontev.shop.dto.response.JwtAuthenticationResponseDto;
import ru.leontev.shop.security.JwtTokenProvider;
import jakarta.validation.Valid;

/**
 * AuthControllerRequest предоставляет REST API для аутентификации.
 * Взаимодействует с AuthenticationManager для проверки учетных данных и JwtTokenProvider для генерации JWT токена.
 * Эндпоинт: POST /api/auth/login.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Эндпоинты для аутентификации пользователей")
public class AuthControllerRequest {

    private final AuthenticationManager authenticationManager; // Менеджер аутентификации, проверяет учетные данные
    private final JwtTokenProvider tokenProvider; // Провайдер для генерации и валидации JWT токенов

    public AuthControllerRequest(AuthenticationManager authenticationManager,
                                 JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Проверяет учетные данные, генерирует и возвращает JWT токен при успешной аутентификации"
    )
    public ResponseEntity<JwtAuthenticationResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            // Генерируем JWT токен после успешной аутентификации
            String token = tokenProvider.generateToken(authentication.getName());
            return ResponseEntity.ok(new JwtAuthenticationResponseDto(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
