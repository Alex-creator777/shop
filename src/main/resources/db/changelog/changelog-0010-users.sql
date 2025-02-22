-- liquibase formatted sql

-- changeset your_name:10
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,                        -- Идентификатор пользователя
                                     username VARCHAR(255) NOT NULL UNIQUE,        -- Уникальное имя пользователя
    password VARCHAR(255) NOT NULL,               -- Зашифрованный пароль
    created_at TIMESTAMP NOT NULL DEFAULT now()   -- Дата создания пользователя
    );

-- changeset your_name:11
CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id INT NOT NULL,                         -- Внешний ключ на таблицу users
                                          role VARCHAR(255) NOT NULL,                   -- Роль пользователя
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users (id)
    );
