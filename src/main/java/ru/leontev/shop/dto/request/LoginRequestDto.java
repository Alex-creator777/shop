package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * LoginRequestDto содержит данные для аутентификации пользователя.
 * Используется в AuthController для входа в систему.
 */
@Data
public class LoginRequestDto {
    @NotBlank
    private String username; // Имя пользователя

    @NotBlank
    private String password; // Пароль
}
