package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leontev.shop.dto.response.TransactionResponseDto;
import ru.leontev.shop.model.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionResponseMapper {
    @Mapping(target = "paymentId", source = "payment.id")
    TransactionResponseDto transactionEntityToTransactionResponseDto(TransactionEntity entity);
}
