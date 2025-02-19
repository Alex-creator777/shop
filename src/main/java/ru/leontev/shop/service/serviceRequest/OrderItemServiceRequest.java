package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.OrderItemRequestDto;
import ru.leontev.shop.dto.response.OrderItemResponseDto;
import ru.leontev.shop.mapper.response.OrderItemResponseMapper;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.model.OrderItemEntity;
import ru.leontev.shop.model.ProductEntity;
import ru.leontev.shop.repository.OrderItemRepository;
import ru.leontev.shop.repository.OrderRepository;
import ru.leontev.shop.repository.ProductRepository;
import ru.leontev.shop.service.calculations.OrderCalculator;

@Service
public class OrderItemServiceRequest {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderItemResponseMapper orderItemResponseMapper;

    public OrderItemServiceRequest(OrderRepository orderRepository,
                                   OrderItemRepository orderItemRepository,
                                   ProductRepository productRepository,
                                   OrderItemResponseMapper orderItemResponseMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderItemResponseMapper = orderItemResponseMapper;
    }

    @Transactional
    public OrderItemResponseDto addOrderItem(OrderItemRequestDto dto) {
        // Получаем заказ по orderId
        OrderEntity order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Получаем товар по productId
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Создаем новую позицию заказа
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setOrder(order);
        orderItem.setProductEntity(product);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUnitPrice(dto.getUnitPrice());

        // Сохраняем позицию заказа
        OrderItemEntity savedItem = orderItemRepository.save(orderItem);

        // Пересчитываем итоговую сумму заказа на основе всех позиций
        order.setTotalAmount(OrderCalculator.calculateTotalAmount(order.getOrderItemEntities()));
        orderRepository.save(order);

        // Возвращаем ответный DTO
        return orderItemResponseMapper.orderItemToOrderItemDto(savedItem);
    }
}
