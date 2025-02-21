package ru.leontev.shop.mapper.request;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.request.TransactionRequestDto;
import ru.leontev.shop.model.PaymentEntity;
import ru.leontev.shop.model.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionRequestMapper {
    default TransactionEntity toEntity(TransactionRequestDto dto, PaymentEntity payment) {
        if (dto == null) {
            return null;
        }
        TransactionEntity entity = new TransactionEntity();
        entity.setPayment(payment);
        entity.setEventType(dto.getEventType());
        entity.setAmount(dto.getAmount());
        entity.setMessage(dto.getMessage());
        entity.setEventDate(java.time.LocalDateTime.now());
        return entity;
    }
}
