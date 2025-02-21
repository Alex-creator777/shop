package ru.leontev.shop.service.calculations;

import org.springframework.stereotype.Service;
import ru.leontev.shop.model.OrderDiscountEntity;
import ru.leontev.shop.repository.OrderDiscountRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDiscountCalculator – сервис для расчёта итоговой суммы заказа с учётом применённых скидок.
 *
 * Этот класс взаимодействует с OrderDiscountRepository для получения всех записей скидок (OrderDiscountEntity),
 * которые были применены к заказу. Он суммирует все отдельные скидки (поле discountValue),
 * ограничивает их суммарное значение максимумом 100% и вычисляет итоговую сумму заказа,
 * уменьшая исходную сумму на рассчитанный процент скидки.
 *
 * Этот сервис является частью слоя расчётов (calculations) и может вызываться из других сервисов (например, OrderService)
 * для получения итоговой суммы заказа после применения скидок.
 */
@Service
public class OrderDiscountCalculator {

    // Репозиторий для получения записей скидок, применённых к заказу
    private final OrderDiscountRepository orderDiscountRepository;

    public OrderDiscountCalculator(OrderDiscountRepository orderDiscountRepository) {
        this.orderDiscountRepository = orderDiscountRepository;
    }

    /**
     * Рассчитывает итоговую сумму заказа после применения всех скидок.
     *
     * @param orderId      Идентификатор заказа, для которого рассчитываются скидки.
     * @param initialTotal Исходная сумма заказа до применения скидок.
     * @return Итоговая сумма заказа после применения скидок.
     */
    public BigDecimal calculateFinalOrderTotal(Long orderId, BigDecimal initialTotal) {
        // Получаем список скидок, применённых к заказу по его ID
        List<OrderDiscountEntity> discounts = orderDiscountRepository.findByOrder_Id(orderId);

        // Суммируем значения скидок (в процентах)
        double totalDiscountPercentage = discounts.stream()
                .mapToDouble(OrderDiscountEntity::getDiscountValue)
                .sum();

        // Ограничиваем общий процент скидки значением 100%
        if (totalDiscountPercentage > 100.0) {
            totalDiscountPercentage = 100.0;
        }

        // Вычисляем итоговую сумму заказа с учётом скидки
        BigDecimal discountFactor = BigDecimal.valueOf((100.0 - totalDiscountPercentage) / 100.0);
        return initialTotal.multiply(discountFactor);
    }
}
