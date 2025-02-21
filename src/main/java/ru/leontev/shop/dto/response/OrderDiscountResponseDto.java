package ru.leontev.shop.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * OrderDiscountResponseDto используется для возврата данных о примененной скидке к заказу.
 * Содержит информацию об идентификаторах заказа и скидки, значении скидки и времени применения.
 */
@Data
public class OrderDiscountResponseDto {
    private Long id; // Уникальный идентификатор записи применения скидки
    private Long orderId; // Идентификатор заказа
    private Long discountId; // Идентификатор скидки
    private Double discountValue; // Процент скидки, применённый к заказу
    private LocalDateTime appliedAt; // Время применения скидки
}
