package ru.leontev.shop.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * RegistrationResponseDto содержит данные зарегистрированного пользователя.
 * Отображает идентификатор, имя пользователя и дату регистрации.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDto {
    private Long id; // Идентификатор пользователя
    private String username; // Имя пользователя
    private LocalDateTime createdAt; // Дата и время регистрации
}
