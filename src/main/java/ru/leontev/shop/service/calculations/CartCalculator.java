package ru.leontev.shop.service.calculations;

import ru.leontev.shop.model.CartItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class CartCalculator {

    /**
     * Вычисляет общую сумму корзины, суммируя произведение цены и количества для каждой позиции.
     *
     * @param cartItems список позиций корзины
     * @return общая сумма корзины
     */
    public static BigDecimal calculateTotalAmount(List<CartItemEntity> cartItems) {
        return cartItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
