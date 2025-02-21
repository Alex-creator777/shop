package ru.leontev.shop.dto.request;

import lombok.Data;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

/**
 * DiscountRequestDto используется для создания или обновления скидки.
 * Содержит данные, необходимые для задания условий скидки.
 */
@Data
public class DiscountRequestDto {

    @NotNull(message = "Code is required")
    private String code; // Промокод, например, "SALE10"

    private String description; // Описание скидки

    @NotNull(message = "Discount percentage is required")
    private Double discountPercentage; // Процент скидки

    private LocalDateTime validFrom; // Дата и время начала действия скидки

    private LocalDateTime validTo; // Дата и время окончания действия скидки

    private String conditions; // Дополнительные условия для применения скидки

    @NotNull(message = "isActive flag is required")
    private Boolean isActive; // Флаг, показывающий, активна ли скидка
}
