package ru.leontev.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Автоматический метод Spring Data JPA без @Query который применяет WHERE is_deleted = false
    Page<ProductEntity> findByIsDeletedFalse(Pageable pageable);
}

