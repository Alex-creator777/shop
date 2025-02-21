package ru.leontev.shop.model;

import java.util.Arrays;

public enum OrderStatus {
    NEW(10),
    PAYED(20),
    SHIPPED(30),
    DELIVERED(40),
    CANCELED(50);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static OrderStatus fromCode(int code) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown status code: " + code));
    }
}
