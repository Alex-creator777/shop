package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.RegistrationResponseDto;
import ru.leontev.shop.model.UserEntity;

/**
 * UserRegistrationResponseMapper преобразует UserEntity в RegistrationResponseDto.
 * Отображает основные данные зарегистрированного пользователя.
 */
@Mapper(componentModel = "spring")
public interface UserRegistrationResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "createdAt", source = "createdAt")
    RegistrationResponseDto toDto(UserEntity user);
}
