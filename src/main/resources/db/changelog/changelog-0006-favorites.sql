-- liquibase formatted sql

-- changeset your_name:6
CREATE TABLE IF NOT EXISTS favorites (
                                         id SERIAL PRIMARY KEY,
                                         customer_id INT NOT NULL,
                                         product_id INT NOT NULL,
                                         added_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_favorite_customer FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT fk_favorite_product FOREIGN KEY (product_id) REFERENCES products (id)
    );
