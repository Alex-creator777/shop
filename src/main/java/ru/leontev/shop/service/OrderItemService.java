package ru.leontev.shop.service;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.mapper.response.OrderItemResponseMapper;
import ru.leontev.shop.model.OrderItemEntity;
import ru.leontev.shop.repository.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemResponseMapper orderItemResponseMapper;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemResponseMapper orderItemResponseMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemResponseMapper = orderItemResponseMapper;
    }

    public List<OrderItemResponseDto> getAllOrderItems() {
        List<OrderItemEntity> orderItems = orderItemRepository.findAll();
        return orderItemResponseMapper.orderItemsToOrderItemDtos(orderItems);
    }

    public OrderItemResponseDto getOrderItemById(Long id) {
        OrderItemEntity orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        return orderItemResponseMapper.orderItemToOrderItemDto(orderItem);
    }
}
