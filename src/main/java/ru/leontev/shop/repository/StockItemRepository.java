package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.StockItemEntity;

public interface StockItemRepository extends JpaRepository<StockItemEntity, Long> {
}
