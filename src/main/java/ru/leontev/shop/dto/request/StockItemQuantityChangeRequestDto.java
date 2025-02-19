package ru.leontev.shop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockItemQuantityChangeRequestDto {
    @NotNull(message = "Stock item ID is required")
    private Long stockItemId;

    /**
     * Значение изменения количества:
     * положительное – увеличение,
     * отрицательное – уменьшение.
     */
    @NotNull(message = "Quantity change is required")
    private Integer quantityChange;
}
