package ru.leontev.shop.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * OrderDiscountRequestDto используется для применения скидки к заказу.
 * Содержит идентификаторы заказа и скидки, а также фиксированный процент скидки, который будет применён.
 */
@Data
public class OrderDiscountRequestDto {

    @NotNull(message = "Order ID is required")
    private Long orderId; // Идентификатор заказа

    @NotNull(message = "Discount ID is required")
    private Long discountId; // Идентификатор скидки

    @NotNull(message = "Discount value is required")
    private Double discountValue; // Процент скидки, применённый к заказу
}
