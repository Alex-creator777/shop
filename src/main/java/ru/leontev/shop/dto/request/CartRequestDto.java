package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartRequestDto {
    @NotNull(message = "User ID is required")
    private Long userId;
}
