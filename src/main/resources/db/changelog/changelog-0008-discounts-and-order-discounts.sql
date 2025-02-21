-- liquibase formatted sql

-- changeset your_name:8
CREATE TABLE IF NOT EXISTS discounts (
                                         id SERIAL PRIMARY KEY,
                                         code VARCHAR(255) NOT NULL UNIQUE, -- Промокод, например, "SALE10"
    description TEXT,                  -- Описание скидки
    discount_percentage DOUBLE PRECISION NOT NULL, -- Процент скидки, например, 10.0 для 10%
    valid_from TIMESTAMP,              -- Дата и время начала действия скидки
    valid_to TIMESTAMP,                -- Дата и время окончания действия скидки
    conditions TEXT,                   -- Дополнительные условия применения скидки
    is_active BOOLEAN NOT NULL         -- Флаг, активна ли скидка
    );

-- changeset your_name:9
CREATE TABLE IF NOT EXISTS order_discounts (
                                               id SERIAL PRIMARY KEY,
                                               order_id INT NOT NULL,             -- Идентификатор заказа
                                               discount_id INT NOT NULL,          -- Идентификатор скидки из таблицы discounts
                                               applied_at TIMESTAMP NOT NULL DEFAULT now(), -- Дата и время применения скидки
    discount_value DOUBLE PRECISION NOT NULL,      -- Фиксированный процент скидки, применённый к заказу
    CONSTRAINT fk_order_discount_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_discount_discount FOREIGN KEY (discount_id) REFERENCES discounts(id)
    );
