package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.DiscountEntity;

/**
 * DiscountRepository обеспечивает CRUD-операции для DiscountEntity.
 */
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
    DiscountEntity findByCode(String code); // Метод для поиска скидки по промокоду
}
