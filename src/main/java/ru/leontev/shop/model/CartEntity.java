package ru.leontev.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Если пользователь авторизован, здесь может храниться его ID.
    @Column(name = "user_id")
    private Long userId;

    // Дата создания корзины
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Дата последнего обновления корзины
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Связь с позициями корзины (пока можно оставить для будущего расширения)
    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItemEntities;

}
