package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.WarehouseEntity;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
}
