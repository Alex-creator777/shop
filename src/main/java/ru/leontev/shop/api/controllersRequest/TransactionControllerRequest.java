package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.TransactionRequestDto;
import ru.leontev.shop.dto.response.TransactionResponseDto;
import ru.leontev.shop.service.serviceRequest.TransactionServiceRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionControllerRequest {

    private final TransactionServiceRequest transactionServiceRequest;

    public TransactionControllerRequest(TransactionServiceRequest transactionServiceRequest) {
        this.transactionServiceRequest = transactionServiceRequest;
    }

    @Operation(summary = "Логирует событие транзакции")
    @PostMapping
    public ResponseEntity<TransactionResponseDto> logTransaction(@Valid @RequestBody TransactionRequestDto dto) {
        TransactionResponseDto responseDto = transactionServiceRequest.logTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
