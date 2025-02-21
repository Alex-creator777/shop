package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.PaymentResponseDto;
import ru.leontev.shop.mapper.response.PaymentResponseMapper;
import ru.leontev.shop.model.PaymentEntity;
import ru.leontev.shop.repository.PaymentRepository;

@Service
public class PaymentServiceResponse {
    private final PaymentRepository paymentRepository;
    private final PaymentResponseMapper paymentResponseMapper;

    public PaymentServiceResponse(PaymentRepository paymentRepository, PaymentResponseMapper paymentResponseMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentResponseMapper = paymentResponseMapper;
    }

    public PaymentResponseDto getPaymentByOrderId(Long orderId) {
        PaymentEntity payment = paymentRepository.findByOrder_Id(orderId);
        if (payment == null) {
            throw new RuntimeException("Payment not found for order id: " + orderId);
        }
        return paymentResponseMapper.paymentEntityToPaymentResponseDto(payment);
    }

    public PaymentResponseDto getPaymentById(Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return paymentResponseMapper.paymentEntityToPaymentResponseDto(payment);
    }
}
