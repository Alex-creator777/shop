package ru.leontev.shop.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DiscountResponseDto используется для возврата данных о скидке клиенту.
 * Содержит информацию об идентификаторе скидки, промокоде, описании, проценте скидки, периоде действия, условиях и активности.
 */
@Data
public class DiscountResponseDto {
    private Long id; // Уникальный идентификатор скидки
    private String code; // Промокод
    private String description; // Описание скидки
    private Double discountPercentage; // Процент скидки
    private LocalDateTime validFrom; // Начало действия скидки
    private LocalDateTime validTo; // Окончание действия скидки
    private String conditions; // Дополнительные условия
    private Boolean isActive; // Статус активности скидки
}
