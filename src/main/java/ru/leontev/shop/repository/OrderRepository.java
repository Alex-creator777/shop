package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
