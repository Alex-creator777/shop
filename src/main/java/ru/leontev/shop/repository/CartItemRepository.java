package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
