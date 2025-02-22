package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.RegistrationRequestDto;
import ru.leontev.shop.model.UserEntity;

/**
 * UserRegistrationRequestMapper преобразует RegistrationRequestDto в UserEntity.
 * Используется в сервисе регистрации для создания новой сущности пользователя.
 */
@Mapper(componentModel = "spring")
public interface UserRegistrationRequestMapper {
    default UserEntity toEntity(RegistrationRequestDto dto) {
        if (dto == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // Пароль будет зашифрован в сервисе регистрации
        return user;
    }
}
