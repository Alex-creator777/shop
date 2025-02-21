package ru.leontev.shop.service.serviceRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.PaymentRequestDto;
import ru.leontev.shop.dto.response.PaymentResponseDto;
import ru.leontev.shop.mapper.request.PaymentRequestMapper;
import ru.leontev.shop.mapper.response.PaymentResponseMapper;
import ru.leontev.shop.model.OrderEntity;
import ru.leontev.shop.model.PaymentEntity;
import ru.leontev.shop.model.PaymentStatus;
import ru.leontev.shop.repository.OrderRepository;
import ru.leontev.shop.repository.PaymentRepository;

@Service
public class PaymentServiceRequest {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentRequestMapper paymentRequestMapper;
    private final PaymentResponseMapper paymentResponseMapper;

    public PaymentServiceRequest(PaymentRepository paymentRepository, OrderRepository orderRepository,
                                 PaymentRequestMapper paymentRequestMapper, PaymentResponseMapper paymentResponseMapper) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentRequestMapper = paymentRequestMapper;
        this.paymentResponseMapper = paymentResponseMapper;
    }

    @Transactional
    public PaymentResponseDto createPayment(PaymentRequestDto dto) {
        OrderEntity order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        PaymentEntity paymentEntity = paymentRequestMapper.toEntity(dto, order);
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity);
        return paymentResponseMapper.paymentEntityToPaymentResponseDto(savedPayment);
    }

    @Transactional
    public PaymentResponseDto updatePaymentStatus(Long paymentId, Integer newStatus, String transactionId, String errorMessage) {
        PaymentEntity payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setPaymentStatus(newStatus);
        payment.setTransactionId(transactionId);
        payment.setErrorMessage(errorMessage);
        payment.setPaymentDate(java.time.LocalDateTime.now());
        PaymentEntity updated = paymentRepository.save(payment);
        return paymentResponseMapper.paymentEntityToPaymentResponseDto(updated);
    }
}
