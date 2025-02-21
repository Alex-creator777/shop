package ru.leontev.shop.api.controllersResponse;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.FavoriteResponseDto;
import ru.leontev.shop.service.serviceResponse.FavoriteServiceResponse;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteControllerResponse {

    private final FavoriteServiceResponse favoriteServiceResponse;

    public FavoriteControllerResponse(FavoriteServiceResponse favoriteServiceResponse) {
        this.favoriteServiceResponse = favoriteServiceResponse;
    }

    @Operation(summary = "Получает список избранных товаров для пользователя")
    @GetMapping
    public List<FavoriteResponseDto> getFavoritesByCustomer(@RequestParam Long customerId) {
        return favoriteServiceResponse.getFavoritesByCustomer(customerId);
    }
}
