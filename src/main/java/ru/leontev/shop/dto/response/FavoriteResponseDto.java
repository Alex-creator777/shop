package ru.leontev.shop.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FavoriteResponseDto {

    private Long id;
    private Long customerId;
    private Long productId;
    // Данные о товаре – используем существующий DTO для товара
    private ProductResponseDto product;
    private LocalDateTime addedAt;
}
