package ru.leontev.shop.dto.response;

import lombok.Data;

/**
 * JwtAuthenticationResponseDto содержит JWT токен, который возвращается клиенту
 * после успешного входа в систему.
 */
@Data
public class JwtAuthenticationResponseDto {
    private String token; // JWT токен

    public JwtAuthenticationResponseDto(String token) {
        this.token = token;
    }
}
