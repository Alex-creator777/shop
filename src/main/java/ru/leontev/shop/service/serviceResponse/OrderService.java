package ru.leontev.shop.service.serviceResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.mapper.response.OrderResponseMapper;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.repository.OrderRepository;
import ru.leontev.shop.service.calculations.OrderCalculator;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderResponseMapper orderResponseMapper;

    public OrderService(OrderRepository orderRepository, OrderResponseMapper orderResponseMapper) {
        this.orderRepository = orderRepository;
        this.orderResponseMapper = orderResponseMapper;
    }

    // Метод для получения всех заказов с пагинацией с автоматическим пересчётом totalAmount
    public Page<OrderResponseDto> getAllOrders(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 0;
        int defaultSize = (size != null) ? size : 10;

        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by("orderDate").descending());
        Page<OrderEntity> ordersPage = orderRepository.findAll(pageable);

        // Для каждого заказа пересчитываем итоговую сумму на основе позиций заказа
        ordersPage.forEach(order -> {
            BigDecimal totalAmount = OrderCalculator.calculateTotalAmount(order.getOrderItemEntities());
            order.setTotalAmount(totalAmount);
        });

        return ordersPage.map(orderResponseMapper::orderToOrderDto);
    }

    // Метод для получения заказа по id с автоматическим пересчётом totalAmount
    public OrderResponseDto getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        BigDecimal totalAmount = OrderCalculator.calculateTotalAmount(order.getOrderItemEntities());
        order.setTotalAmount(totalAmount);
        return orderResponseMapper.orderToOrderDto(order);
    }
}
