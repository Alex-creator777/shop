package ru.leontev.shop.model;

import java.util.Arrays;

public enum OrderStatus {
    NEW(10),
    PROCESSING(20),
    DELIVERED(30);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    // Явно прописанный геттер (заменяет Lombok)
    public int getCode() {
        return this.code;
    }

    // Метод для получения статуса по числовому коду
    public static OrderStatus fromCode(int code) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown status code: " + code));
    }
}
