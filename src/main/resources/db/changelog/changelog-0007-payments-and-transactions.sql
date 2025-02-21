-- liquibase formatted sql

-- changeset your_name:7
CREATE TABLE IF NOT EXISTS payments (
                                        id              SERIAL PRIMARY KEY,
                                        order_id        INT NOT NULL,
                                        payment_status  INT NOT NULL,
                                        payment_method  VARCHAR(255) NOT NULL,
    transaction_id  VARCHAR(255),
    payment_date    TIMESTAMP NOT NULL DEFAULT now(),
    amount          DECIMAL(10,2) NOT NULL,
    error_message   VARCHAR(1024),
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES orders (id)
    );

-- changeset your_name:8
CREATE TABLE IF NOT EXISTS transactions (
                                            id              SERIAL PRIMARY KEY,
                                            payment_id      INT NOT NULL,
                                            event_type      VARCHAR(255) NOT NULL,
    event_date      TIMESTAMP NOT NULL DEFAULT now(),
    amount          DECIMAL(10,2) NOT NULL,
    message         VARCHAR(1024),
    CONSTRAINT fk_transaction_payment FOREIGN KEY (payment_id) REFERENCES payments (id)
    );
