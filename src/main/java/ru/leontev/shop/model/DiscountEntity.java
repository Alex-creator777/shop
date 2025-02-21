package ru.leontev.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * DiscountEntity представляет собой скидку или промокод.
 * Содержит условия скидки, такие как код, описание, процент скидки, период действия, условия применения и статус активности.
 * Используется при применении скидки к заказу через сущность OrderDiscountEntity.
 */
@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор скидки

    @Column(name = "code", nullable = false, unique = true)
    private String code; // Промокод, например, "SALE10"

    @Column(name = "description")
    private String description; // Описание условий скидки

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage; // Процент скидки (например, 10.0 означает 10% скидку)

    @Column(name = "valid_from")
    private LocalDateTime validFrom; // Дата и время начала действия скидки

    @Column(name = "valid_to")
    private LocalDateTime validTo; // Дата и время окончания действия скидки

    @Column(name = "conditions")
    private String conditions; // Дополнительные условия (например, минимальная сумма заказа или ограничение по категориям)

    @Column(name = "is_active", nullable = false)
    private Boolean isActive; // Флаг, указывающий, активна ли скидка
}
