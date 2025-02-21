package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.TransactionRequestDto;
import ru.leontev.shop.dto.response.TransactionResponseDto;
import ru.leontev.shop.mapper.request.TransactionRequestMapper;
import ru.leontev.shop.mapper.response.TransactionResponseMapper;
import ru.leontev.shop.model.PaymentEntity;
import ru.leontev.shop.model.TransactionEntity;
import ru.leontev.shop.repository.PaymentRepository;
import ru.leontev.shop.repository.TransactionRepository;

@Service
public class TransactionServiceRequest {
    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;
    private final TransactionRequestMapper transactionRequestMapper;
    private final TransactionResponseMapper transactionResponseMapper;

    public TransactionServiceRequest(TransactionRepository transactionRepository,
                                     PaymentRepository paymentRepository,
                                     TransactionRequestMapper transactionRequestMapper,
                                     TransactionResponseMapper transactionResponseMapper) {
        this.transactionRepository = transactionRepository;
        this.paymentRepository = paymentRepository;
        this.transactionRequestMapper = transactionRequestMapper;
        this.transactionResponseMapper = transactionResponseMapper;
    }

    @Transactional
    public TransactionResponseDto logTransaction(TransactionRequestDto dto) {
        PaymentEntity payment = paymentRepository.findById(dto.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        TransactionEntity entity = transactionRequestMapper.toEntity(dto, payment);
        TransactionEntity saved = transactionRepository.save(entity);
        return transactionResponseMapper.transactionEntityToTransactionResponseDto(saved);
    }
}
