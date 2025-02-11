package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
