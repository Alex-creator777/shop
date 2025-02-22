package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.ReviewRequestDto;
import ru.leontev.shop.model.CustomerEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.model.ReviewEntity;

/**
 * ReviewRequestMapper преобразует данные из ReviewRequestDto в ReviewEntity.
 * Для корректного формирования отзыва требуются сущности ProductEntity и CustomerEntity,
 * чтобы установить связи с товаром и клиентом соответственно. Используется в ReviewServiceRequest.
 */
@Mapper(componentModel = "spring")
public interface ReviewRequestMapper {
    default ReviewEntity toEntity(ReviewRequestDto dto, ProductEntity product, CustomerEntity customer) {
        if (dto == null) {
            return null;
        }
        ReviewEntity entity = new ReviewEntity();
        entity.setProduct(product); // Устанавливает связь с товаром
        entity.setCustomer(customer); // Устанавливает связь с клиентом
        entity.setReviewText(dto.getReviewText()); // Устанавливает текст отзыва
        entity.setRating(dto.getRating()); // Устанавливает оценку отзыва
        entity.setCreatedAt(java.time.LocalDateTime.now()); // Устанавливает дату создания отзыва
        return entity;
    }
}
