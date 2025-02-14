-- liquibase formatted sql

-- changeset your_name:3
ALTER TABLE products ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE;
