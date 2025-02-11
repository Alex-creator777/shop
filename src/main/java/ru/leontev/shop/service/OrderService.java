package ru.leontev.shop.service;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.OrderResponseDto;
import ru.leontev.shop.mapper.response.OrderResponseMapper;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderResponseMapper orderResponseMapper;

    public OrderService(OrderRepository orderRepository, OrderResponseMapper orderResponseMapper) {
        this.orderRepository = orderRepository;
        this.orderResponseMapper = orderResponseMapper;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderResponseMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderResponseDto getOrderById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderResponseMapper.orderToOrderDto(order);
    }
}
