-- liquibase formatted sql

-- changeset your_name:5
-- Создаем таблицу carts для хранения корзин
CREATE TABLE IF NOT EXISTS carts (
                                     id SERIAL PRIMARY KEY,
                                     user_id INT, -- если требуется, можно заменить на BIGINT
                                     created_at TIMESTAMP NOT NULL,
                                     updated_at TIMESTAMP NOT NULL
);

-- changeset your_name:6
-- Создаем таблицу cart_items для хранения позиций корзины
CREATE TABLE IF NOT EXISTS cart_items (
                                          id SERIAL PRIMARY KEY,
                                          cart_id INT NOT NULL,
                                          product_id INT NOT NULL,
                                          quantity INT NOT NULL,
                                          price DECIMAL(10,2) NOT NULL,
    added_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts (id)
    );
