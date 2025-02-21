package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.TransactionResponseDto;
import ru.leontev.shop.service.serviceResponse.TransactionServiceResponse;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionControllerResponse {

    private final TransactionServiceResponse transactionServiceResponse;

    public TransactionControllerResponse(TransactionServiceResponse transactionServiceResponse) {
        this.transactionServiceResponse = transactionServiceResponse;
    }

    @Operation(summary = "Получает лог транзакций по ID платежа")
    @GetMapping("/payment/{paymentId}")
    public List<TransactionResponseDto> getTransactionsByPayment(@PathVariable Long paymentId) {
        return transactionServiceResponse.getTransactionsByPaymentId(paymentId);
    }
}
