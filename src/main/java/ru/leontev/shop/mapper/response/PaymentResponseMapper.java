package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.PaymentResponseDto;
import ru.leontev.shop.model.PaymentEntity;

@Mapper(componentModel = "spring")
public interface PaymentResponseMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "paymentStatus", expression = "java(paymentEntity.getPaymentStatusEnum().name())")
    PaymentResponseDto paymentEntityToPaymentResponseDto(PaymentEntity paymentEntity);
}
