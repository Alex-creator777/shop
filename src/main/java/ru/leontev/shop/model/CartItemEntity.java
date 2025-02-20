package ru.leontev.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Связь с корзиной
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cartEntity;

    // Идентификатор товара (можно заменить на связь с ProductEntity, если такая сущность есть)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    // Количество товара в позиции
    @Column(nullable = false)
    private Integer quantity;

    // Цена товара на момент добавления в корзину
    @Column(nullable = false)
    private BigDecimal price;

    // Дата и время добавления товара в корзину
    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
