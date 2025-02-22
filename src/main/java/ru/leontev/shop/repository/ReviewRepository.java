package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.ReviewEntity;
import java.util.List;

/**
 * ReviewRepository обеспечивает CRUD-операции для ReviewEntity.
 * Дополнительно предоставляет метод для получения списка отзывов для конкретного товара по его идентификатору.
 */
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByProduct_Id(Long productId); // Находит все отзывы для заданного товара
}
