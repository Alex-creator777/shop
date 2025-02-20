package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO для оформления заказа из корзины.
 * Содержит идентификатор корзины, а также идентификатор клиента (покупателя),
 * для которого оформляется заказ.
 */
@Data
public class OrderFinalizationRequestDto {

    @NotNull(message = "Cart ID is required")
    private Long cartId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    // Дополнительные поля (например, адрес доставки, способ оплаты) можно добавить при необходимости.
}
