package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * RegistrationRequestDto содержит данные для регистрации нового пользователя.
 * Включает обязательные поля username и password.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {

    @NotBlank(message = "Username is required")
    private String username; // Имя пользователя, должно быть уникальным

    @NotBlank(message = "Password is required")
    private String password; // Пароль (в исходном виде, который будет зашифрован)
}
