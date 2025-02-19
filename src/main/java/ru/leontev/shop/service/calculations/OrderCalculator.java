package ru.leontev.shop.service.calculations;

import ru.leontev.shop.model.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderCalculator {

    /**
     * Вычисляет итоговую сумму заказа на основе позиций.
     * Для каждой позиции умножает цену за единицу на количество и суммирует результаты.
     *
     * @param orderItems список позиций заказа
     * @return итоговая сумма заказа
     */
    public static BigDecimal calculateTotalAmount(List<OrderItemEntity> orderItems) {
        return orderItems.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
