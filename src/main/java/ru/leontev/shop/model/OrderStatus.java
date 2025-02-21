package ru.leontev.shop.model;

import java.util.Arrays;
/**

 Этот enum определяет возможные статусы заказа в системе, присваивая каждому статусу числовой код (например, NEW=10, PAYED=20, SHIPPED=30, DELIVERED=40, CANCELED=50). Он используется для сохранения и извлечения статуса заказа из базы данных: при сохранении заказа метод getCode() возвращает числовое представление, а метод fromCode() преобразует полученный числовой код обратно в соответствующий enum-статус. Таким образом, OrderStatus обеспечивает единообразное представление и обработку состояния заказа на всех уровнях приложения, взаимодействуя с сущностью OrderEntity для корректного отражения жизненного цикла заказа. */

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
