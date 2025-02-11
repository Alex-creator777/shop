package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
