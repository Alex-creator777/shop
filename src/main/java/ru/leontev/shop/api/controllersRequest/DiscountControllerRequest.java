package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.DiscountRequestDto;
import ru.leontev.shop.dto.response.DiscountResponseDto;
import ru.leontev.shop.service.serviceRequest.DiscountServiceRequest;
import jakarta.validation.Valid;

/**
 * DiscountControllerRequest предоставляет REST API для создания и обновления скидок.
 * Взаимодействует с DiscountServiceRequest.
 */
@RestController
@RequestMapping("/api/discounts")
public class DiscountControllerRequest {

    private final DiscountServiceRequest discountServiceRequest;

    public DiscountControllerRequest(DiscountServiceRequest discountServiceRequest) {
        this.discountServiceRequest = discountServiceRequest;
    }

    @Operation(summary = "Создает новую скидку")
    @PostMapping
    public ResponseEntity<DiscountResponseDto> createDiscount(@Valid @RequestBody DiscountRequestDto dto) {
        DiscountResponseDto responseDto = discountServiceRequest.createDiscount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Обновляет существующую скидку")
    @PutMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> updateDiscount(@PathVariable Long id, @Valid @RequestBody DiscountRequestDto dto) {
        DiscountResponseDto responseDto = discountServiceRequest.updateDiscount(id, dto);
        return ResponseEntity.ok(responseDto);
    }
}
