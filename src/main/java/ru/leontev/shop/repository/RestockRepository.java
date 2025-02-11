package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.RestockEntity;

public interface RestockRepository extends JpaRepository<RestockEntity, Long> {
}
