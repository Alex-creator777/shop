package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.ProductResponseDto;
import ru.leontev.shop.model.*;

// Аннотация @Mapper сообщает MapStruct, что этот интерфейс должен быть использован для создания маппера с поддержкой автогенерации кода
@Mapper(componentModel = "spring") // componentModel = "spring" позволяет использовать этот маппер как Spring Bean (инжектировать его в сервисы)
public interface ProductResponseMapper {

    // Этот метод конвертирует сущность ProductEntity в DTO ProductDto
    // MapStruct автоматически генерирует код, который будет делать все преобразования для каждого поля
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponseDto productToProductDto(ProductEntity productEntity);

}
