package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.RegistrationRequestDto;
import ru.leontev.shop.dto.response.RegistrationResponseDto;
import ru.leontev.shop.service.serviceRequest.UserRegistrationServiceRequest;
import jakarta.validation.Valid;

/**
 * RegistrationControllerRequest предоставляет REST API для регистрации новых пользователей.
 * Взаимодействует с UserRegistrationServiceRequest для создания пользователя в базе.
 * Эндпоинт: POST /api/auth/register.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Registration", description = "Эндпоинты для регистрации новых пользователей")
public class RegistrationControllerRequest {

    private final UserRegistrationServiceRequest registrationServiceRequest; // Сервис для регистрации пользователей

    public RegistrationControllerRequest(UserRegistrationServiceRequest registrationServiceRequest) {
        this.registrationServiceRequest = registrationServiceRequest;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Принимает данные регистрации, шифрует пароль, устанавливает роль по умолчанию и сохраняет пользователя в базе."
    )
    public ResponseEntity<RegistrationResponseDto> registerUser(@Valid @RequestBody RegistrationRequestDto dto) {
        RegistrationResponseDto response = registrationServiceRequest.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
