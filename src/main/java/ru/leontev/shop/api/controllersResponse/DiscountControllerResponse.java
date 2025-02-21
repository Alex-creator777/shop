package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.DiscountResponseDto;
import ru.leontev.shop.service.serviceResponse.DiscountServiceResponse;
import java.util.List;

/**
 * DiscountControllerResponse предоставляет REST API для получения данных о скидках.
 * Взаимодействует с DiscountServiceResponse.
 */
@RestController
@RequestMapping("/discounts")
public class DiscountControllerResponse {

    private final DiscountServiceResponse discountServiceResponse;

    public DiscountControllerResponse(DiscountServiceResponse discountServiceResponse) {
        this.discountServiceResponse = discountServiceResponse;
    }

    @Operation(summary = "Получает скидку по ID")
    @GetMapping("/{id}")
    public DiscountResponseDto getDiscountById(@PathVariable Long id) {
        return discountServiceResponse.getDiscountById(id);
    }

    @Operation(summary = "Получает все скидки")
    @GetMapping
    public List<DiscountResponseDto> getAllDiscounts() {
        return discountServiceResponse.getAllDiscounts();
    }
}
