package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.PaymentRequestDto;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.model.PaymentEntity;
import ru.leontev.shop.model.PaymentStatus;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper {
    default PaymentEntity toEntity(PaymentRequestDto dto, OrderEntity order) {
        if (dto == null) {
            return null;
        }
        PaymentEntity entity = new PaymentEntity();
        entity.setOrder(order);
        // Если в DTO указан статус, используем его; иначе – по умолчанию PENDING
        if (dto.getPaymentStatus() != null) {
            entity.setPaymentStatus(dto.getPaymentStatus());
        } else {
            entity.setPaymentStatus(PaymentStatus.PENDING.getCode());
        }
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setAmount(dto.getAmount());
        entity.setTransactionId(dto.getTransactionId());
        entity.setErrorMessage(dto.getErrorMessage());
        entity.setPaymentDate(java.time.LocalDateTime.now());
        return entity;
    }
}
