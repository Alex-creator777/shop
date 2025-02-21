package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.OrderDiscountRequestDto;
import ru.leontev.shop.dto.response.OrderDiscountResponseDto;
import ru.leontev.shop.mapper.request.OrderDiscountRequestMapper;
import ru.leontev.shop.mapper.response.OrderDiscountResponseMapper;
import ru.leontev.shop.model.OrderDiscountEntity;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.model.DiscountEntity;
import ru.leontev.shop.repository.OrderDiscountRepository;
import ru.leontev.shop.repository.OrderRepository;
import ru.leontev.shop.repository.DiscountRepository;

/**
 * OrderDiscountServiceRequest управляет созданием записи применения скидки к заказу.
 * Взаимодействует с OrderDiscountRepository, OrderRepository, DiscountRepository, OrderDiscountRequestMapper и OrderDiscountResponseMapper.
 */
@Service
public class OrderDiscountServiceRequest {
    private final OrderDiscountRepository orderDiscountRepository;
    private final OrderRepository orderRepository;
    private final DiscountRepository discountRepository;
    private final OrderDiscountRequestMapper orderDiscountRequestMapper;
    private final OrderDiscountResponseMapper orderDiscountResponseMapper;

    public OrderDiscountServiceRequest(OrderDiscountRepository orderDiscountRepository,
                                       OrderRepository orderRepository,
                                       DiscountRepository discountRepository,
                                       OrderDiscountRequestMapper orderDiscountRequestMapper,
                                       OrderDiscountResponseMapper orderDiscountResponseMapper) {
        this.orderDiscountRepository = orderDiscountRepository;
        this.orderRepository = orderRepository;
        this.discountRepository = discountRepository;
        this.orderDiscountRequestMapper = orderDiscountRequestMapper;
        this.orderDiscountResponseMapper = orderDiscountResponseMapper;
    }

    @Transactional
    public OrderDiscountResponseDto applyDiscount(OrderDiscountRequestDto dto) {
        OrderEntity order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        DiscountEntity discount = discountRepository.findById(dto.getDiscountId())
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        OrderDiscountEntity entity = orderDiscountRequestMapper.toEntity(dto, order, discount);
        OrderDiscountEntity saved = orderDiscountRepository.save(entity);
        return orderDiscountResponseMapper.toDto(saved);
    }
}
