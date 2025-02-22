package ru.leontev.shop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig настраивает спецификацию OpenAPI для вашего приложения.
 * Здесь определяется информация об API и схема безопасности с именем "bearerAuth".
 * Благодаря этому Swagger UI отобразит кнопку "Authorize", позволяющую вводить JWT-токен.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Shop API", version = "1.0", description = "Документация API для магазина"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth", // Имя схемы, которое будет использоваться в SecurityRequirement
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
