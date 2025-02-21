package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.OrderDiscountResponseDto;
import ru.leontev.shop.mapper.response.OrderDiscountResponseMapper;
import ru.leontev.shop.model.OrderDiscountEntity;
import ru.leontev.shop.repository.OrderDiscountRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderDiscountServiceResponse управляет извлечением данных о скидках, примененных к заказу.
 * Взаимодействует с OrderDiscountRepository и OrderDiscountResponseMapper.
 */
@Service
public class OrderDiscountServiceResponse {
    private final OrderDiscountRepository orderDiscountRepository;
    private final OrderDiscountResponseMapper orderDiscountResponseMapper;

    public OrderDiscountServiceResponse(OrderDiscountRepository orderDiscountRepository, OrderDiscountResponseMapper orderDiscountResponseMapper) {
        this.orderDiscountRepository = orderDiscountRepository;
        this.orderDiscountResponseMapper = orderDiscountResponseMapper;
    }

    public List<OrderDiscountResponseDto> getDiscountsByOrderId(Long orderId) {
        List<OrderDiscountEntity> list = orderDiscountRepository.findByOrder_Id(orderId);
        return list.stream().map(orderDiscountResponseMapper::toDto).collect(Collectors.toList());
    }
}
