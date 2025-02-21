package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.OrderDiscountEntity;
import java.util.List;

/**
 * OrderDiscountRepository обеспечивает CRUD-операции для OrderDiscountEntity.
 */
public interface OrderDiscountRepository extends JpaRepository<OrderDiscountEntity, Long> {
    List<OrderDiscountEntity> findByOrder_Id(Long orderId);
}
