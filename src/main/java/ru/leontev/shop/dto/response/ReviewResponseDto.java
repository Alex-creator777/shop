package ru.leontev.shop.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * ReviewResponseDto используется для передачи данных отзыва клиенту.
 * Содержит информацию об идентификаторе отзыва, связанном товаре и клиенте, тексте отзыва,
 * оценке и дате создания. Преобразуется из ReviewEntity с помощью маппера.
 */
@Data
public class ReviewResponseDto {
    private Long id; // Идентификатор отзыва
    private Long productId; // Идентификатор товара, к которому относится отзыв
    private Long customerId; // Идентификатор клиента, оставившего отзыв
    private String reviewText; // Текст отзыва
    private Integer rating; // Оценка отзыва
    private LocalDateTime createdAt; // Дата и время создания отзыва
}
