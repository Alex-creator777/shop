-- liquibase formatted sql

-- changeset your_name:9
INSERT INTO categories (name, description)
VALUES ('Батареи', 'Энергетические батареи для устройств'),
       ('Кабели', 'Проводные соединения'),
       ('Зарядные устройства', 'Адаптеры питания');

-- changeset your_name:10
INSERT INTO products (name, description, price, voltage, power, connector_type, category_id)
VALUES ('Литиевая батарея', 'Долговечная батарея для электроники', 500.00, 3.7, NULL, NULL, 1),
       ('USB Type-C кабель', 'Быстрая передача данных', 300.00, NULL, NULL, 'Type-C', 2),
       ('Зарядное устройство 65W', 'Для ноутбуков и телефонов', 2500.00, 220, 65, 'Type-C', 3);

-- changeset your_name:11
INSERT INTO warehouses (name, location, capacity)
VALUES ('Главный склад', 'Москва', 1000),
       ('Склад Юг', 'Ростов-на-Дону', 500),
       ('Склад Запад', 'Калининград', 300);

-- changeset your_name:12
INSERT INTO stock_items (product_id, warehouse_id, quantity)
VALUES (1, 1, 50),
       (2, 2, 30),
       (3, 3, 20);

-- changeset your_name:13
INSERT INTO restocks (product_id, warehouse_id, supplied_quantity, supplier_info)
VALUES (1, 1, 100, 'ООО "ЭнергоПоставка"'),
       (2, 2, 50, 'АО "Кабельные системы"'),
       (3, 3, 30, 'ЗАО "Зарядка+"');

-- changeset your_name:14
INSERT INTO customers (first_name, last_name, email, address)
VALUES ('Иван', 'Петров', 'ivan.petrov@mail.com', 'Москва, ул. Ленина, 5'),
       ('Ольга', 'Сидорова', 'olga.sidorova@mail.com', 'Ростов-на-Дону, ул. Гагарина, 10'),
       ('Андрей', 'Кузнецов', 'andrey.kuznetsov@mail.com', 'Калининград, ул. Победы, 3');

-- changeset your_name:15
INSERT INTO orders (order_date, status, total_amount, customer_id)
VALUES (now(), 'NEW', 500.00, 1),
       (now(), 'PROCESSING', 800.00, 2),
       (now(), 'DELIVERED', 2500.00, 3);

-- changeset your_name:16
INSERT INTO order_items (order_id, product_id, quantity, unit_price)
VALUES (1, 1, 1, 500.00),
       (2, 2, 2, 300.00),
       (3, 3, 1, 2500.00);
