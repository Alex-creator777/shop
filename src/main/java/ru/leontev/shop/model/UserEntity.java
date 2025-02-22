package ru.leontev.shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * UserEntity представляет пользователя системы.
 * Класс взаимодействует с таблицей users и таблицей user_roles (через ElementCollection),
 * хранит имя пользователя, зашифрованный пароль, роли и дату создания. Используется для проверки
 * учетных данных при аутентификации.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Идентификатор пользователя

    @Column(name = "username", nullable = false, unique = true)
    private String username; // Уникальное имя пользователя

    @Column(name = "password", nullable = false)
    private String password; // Зашифрованный пароль пользователя

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles; // Набор ролей пользователя (например, ROLE_USER, ROLE_ADMIN)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Дата создания пользователя
}
