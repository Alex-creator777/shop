package ru.leontev.shop.service.serviceResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.mapper.response.OrderResponseMapper;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.repository.OrderRepository;
import ru.leontev.shop.service.calculations.OrderCalculator;
import ru.leontev.shop.service.calculations.OrderDiscountCalculator;

import java.math.BigDecimal;

/**
 * OrderService – сервис для обработки заказов.
 *
 * Этот сервис получает заказы из базы данных и автоматически пересчитывает итоговую сумму заказа.
 * Сначала вычисляется сумма по позициям заказа с помощью OrderCalculator, затем OrderDiscountCalculator
 * применяет скидки, что позволяет получить итоговую сумму заказа с учётом скидок.
 * Методы класса аннотированы @Transactional, чтобы лениво загружаемые коллекции (например, orderItemEntities)
 * могли быть инициализированы в рамках открытой транзакции.
 * OrderService вызывается из контроллеров, и конечный пользователь получает обновлённые данные заказа.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderDiscountCalculator orderDiscountCalculator;

    public OrderService(OrderRepository orderRepository, OrderResponseMapper orderResponseMapper, OrderDiscountCalculator orderDiscountCalculator) {
        this.orderRepository = orderRepository;
        this.orderResponseMapper = orderResponseMapper;
        this.orderDiscountCalculator = orderDiscountCalculator;
    }

    /**
     * Получает все заказы с пагинацией и пересчитывает итоговую сумму заказа с учётом скидок.
     *
     * @param page  номер страницы
     * @param size  размер страницы
     * @return страница заказов с обновлённой итоговой суммой
     */
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getAllOrders(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 10;
        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by("orderDate").descending());
        Page<OrderEntity> ordersPage = orderRepository.findAll(pageable);

        // Для каждого заказа:
        // 1. Вычисляем сумму по позициям заказа с помощью OrderCalculator.
        // 2. Применяем скидки через OrderDiscountCalculator и обновляем итоговую сумму.
        ordersPage.forEach(order -> {
            BigDecimal itemsTotal = OrderCalculator.calculateTotalAmount(order.getOrderItemEntities());
            BigDecimal finalTotal = orderDiscountCalculator.calculateFinalOrderTotal(order.getId(), itemsTotal);
            order.setTotalAmount(finalTotal);
        });

        return ordersPage.map(orderResponseMapper::orderToOrderDto);
    }

    /**
     * Получает заказ по ID с пересчитанной итоговой суммой заказа с учётом скидок.
     *
     * @param id идентификатор заказа
     * @return заказ с обновлённой итоговой суммой
     */
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        BigDecimal itemsTotal = OrderCalculator.calculateTotalAmount(order.getOrderItemEntities());
        BigDecimal finalTotal = orderDiscountCalculator.calculateFinalOrderTotal(order.getId(), itemsTotal);
        order.setTotalAmount(finalTotal);
        return orderResponseMapper.orderToOrderDto(order);
    }
}
