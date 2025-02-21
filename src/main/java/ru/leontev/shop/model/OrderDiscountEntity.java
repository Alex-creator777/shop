package ru.leontev.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * OrderDiscountEntity фиксирует применение скидки к заказу.
 * Связывает заказ (OrderEntity) со скидкой (DiscountEntity) и хранит время применения и значение скидки,
 * что позволяет вычислять итоговую сумму заказа с учетом скидки, не изменяя сам OrderEntity.
 */
@Entity
@Table(name = "order_discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор записи применения скидки

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order; // Ссылка на заказ, к которому применяется скидка

    @ManyToOne
    @JoinColumn(name = "discount_id", nullable = false)
    private DiscountEntity discount; // Ссылка на применённую скидку

    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt; // Время применения скидки

    @Column(name = "discount_value", nullable = false)
    private Double discountValue; // Фиксированный процент скидки, применённый к заказу
}
