-- liquibase formatted sql

-- changeset your_name:10
CREATE TABLE IF NOT EXISTS reviews (
                                       id SERIAL PRIMARY KEY,                  -- Идентификатор отзыва
                                       product_id INT NOT NULL,                -- Внешний ключ на таблицу products (товар)
                                       customer_id INT NOT NULL,               -- Внешний ключ на таблицу customers (клиент)
                                       review_text TEXT NOT NULL,              -- Текст отзыва
                                       rating INT NOT NULL,                    -- Оценка отзыва (обычно от 1 до 5)
                                       created_at TIMESTAMP NOT NULL DEFAULT now(),  -- Дата создания отзыва
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_review_customer FOREIGN KEY (customer_id) REFERENCES customers (id)
    );
