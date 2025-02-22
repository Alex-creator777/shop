package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ReviewRequestDto представляет данные запроса для создания нового отзыва.
 * Этот DTO используется в ReviewServiceRequest и ReviewRequestMapper для валидации и преобразования
 * входных данных от клиента в сущность ReviewEntity.
 */
@Data
public class ReviewRequestDto {

    @NotNull(message = "Product ID is required")
    private Long productId; // Идентификатор товара, для которого оставляется отзыв

    @NotNull(message = "Customer ID is required")
    private Long customerId; // Идентификатор клиента, который оставляет отзыв

    @NotNull(message = "Review text is required")
    private String reviewText; // Текст отзыва

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Integer rating; // Оценка отзыва (значение от 1 до 5)
}
