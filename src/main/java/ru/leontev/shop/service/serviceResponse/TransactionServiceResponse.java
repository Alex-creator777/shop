package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.TransactionResponseDto;
import ru.leontev.shop.mapper.response.TransactionResponseMapper;
import ru.leontev.shop.model.TransactionEntity;
import ru.leontev.shop.repository.TransactionRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceResponse {
    private final TransactionRepository transactionRepository;
    private final TransactionResponseMapper transactionResponseMapper;

    public TransactionServiceResponse(TransactionRepository transactionRepository, TransactionResponseMapper transactionResponseMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionResponseMapper = transactionResponseMapper;
    }

    public List<TransactionResponseDto> getTransactionsByPaymentId(Long paymentId) {
        List<TransactionEntity> transactions = transactionRepository.findByPayment_Id(paymentId);
        return transactions.stream()
                .map(transactionResponseMapper::transactionEntityToTransactionResponseDto)
                .collect(Collectors.toList());
    }
}
