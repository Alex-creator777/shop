package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
