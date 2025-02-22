package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.ReviewResponseDto;
import ru.leontev.shop.model.ReviewEntity;

/**
 * ReviewResponseMapper преобразует ReviewEntity в ReviewResponseDto для отправки клиенту.
 * Производится маппинг полей, включая преобразование связей: product -> productId и customer -> customerId.
 */
@Mapper(componentModel = "spring")
public interface ReviewResponseMapper {
    @Mapping(target = "productId", source = "product.id") // Преобразует объект ProductEntity в идентификатор товара
    @Mapping(target = "customerId", source = "customer.id") // Преобразует объект CustomerEntity в идентификатор клиента
    ReviewResponseDto toDto(ReviewEntity reviewEntity);
}
