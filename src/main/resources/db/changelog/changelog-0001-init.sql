-- liquibase formatted sql

-- changeset your_name:1
CREATE TABLE IF NOT EXISTS categories
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
    );

-- changeset your_name:2
CREATE TABLE IF NOT EXISTS products
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(255)   NOT NULL,
    description    TEXT,
    price          DECIMAL(10, 2) NOT NULL,
    voltage        DOUBLE PRECISION,
    power          DOUBLE PRECISION,
    connector_type VARCHAR(255),
    category_id    INT REFERENCES categories (id)
    );

-- changeset your_name:3
CREATE TABLE IF NOT EXISTS warehouses
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    capacity INT
    );

-- changeset your_name:4
CREATE TABLE IF NOT EXISTS stock_items
(
    id                SERIAL PRIMARY KEY,
    product_id        INT REFERENCES products (id),
    warehouse_id      INT REFERENCES warehouses (id),
    quantity          INT NOT NULL,
    last_updated_date TIMESTAMP DEFAULT now()
    );

-- changeset your_name:5
CREATE TABLE IF NOT EXISTS restocks
(
    id                SERIAL PRIMARY KEY,
    product_id        INT REFERENCES products (id),
    warehouse_id      INT REFERENCES warehouses (id),
    supplied_quantity INT NOT NULL,
    restock_date      TIMESTAMP DEFAULT now(),
    supplier_info     VARCHAR(255)
    );

-- changeset your_name:6
CREATE TABLE IF NOT EXISTS customers
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255),
    email      VARCHAR(255) NOT NULL,
    address    TEXT
    );

-- changeset your_name:7
CREATE TABLE IF NOT EXISTS orders
(
    id           SERIAL PRIMARY KEY,
    order_date   TIMESTAMP DEFAULT now(),
    status       INTEGER NOT NULL,
    total_amount DECIMAL(10, 2),
    customer_id  INT REFERENCES customers (id)
    );

-- changeset your_name:8
CREATE TABLE IF NOT EXISTS order_items
(
    id         SERIAL PRIMARY KEY,
    order_id   INT REFERENCES orders (id),
    product_id INT REFERENCES products (id),
    quantity   INT            NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
    );
