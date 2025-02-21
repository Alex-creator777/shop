package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.FavoriteRequestDto;
import ru.leontev.shop.model.CustomerEntity;
import ru.leontev.shop.model.FavoriteEntity;
import ru.leontev.shop.model.ProductEntity;

@Mapper(componentModel = "spring")
public interface FavoriteRequestMapper {
    /**
     * Преобразует FavoriteRequestDto в FavoriteEntity.
     * Для установки связей с CustomerEntity и ProductEntity необходимо передать их как параметры.
     */
    default FavoriteEntity toEntity(FavoriteRequestDto dto, CustomerEntity customer, ProductEntity product) {
        if (dto == null) {
            return null;
        }
        FavoriteEntity entity = new FavoriteEntity();
        entity.setCustomer(customer);
        entity.setProduct(product);
        // Устанавливаем дату добавления как текущее время
        entity.setAddedAt(java.time.LocalDateTime.now());
        return entity;
    }
}
