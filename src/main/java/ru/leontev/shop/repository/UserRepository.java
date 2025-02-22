package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.UserEntity;
import java.util.Optional;

/**
 * UserRepository предоставляет CRUD-операции для UserEntity.
 * Используется для получения пользователя по имени (username) из базы данных.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
