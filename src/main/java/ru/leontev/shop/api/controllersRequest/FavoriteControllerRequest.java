package ru.leontev.shop.api.controllersRequest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.request.FavoriteRequestDto;
import ru.leontev.shop.dto.response.FavoriteResponseDto;
import ru.leontev.shop.service.serviceRequest.FavoriteServiceRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteControllerRequest {

    private final FavoriteServiceRequest favoriteServiceRequest;

    public FavoriteControllerRequest(FavoriteServiceRequest favoriteServiceRequest) {
        this.favoriteServiceRequest = favoriteServiceRequest;
    }

    @Operation(summary = "Добавляет товар в избранное")
    @PostMapping
    public ResponseEntity<FavoriteResponseDto> addFavorite(@Valid @RequestBody FavoriteRequestDto dto) {
        FavoriteResponseDto responseDto = favoriteServiceRequest.addFavorite(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Удаляет товар из избранного по customerId и productId")
    @DeleteMapping
    public ResponseEntity<Void> removeFavorite(@RequestParam Long customerId, @RequestParam Long productId) {
        favoriteServiceRequest.removeFavorite(customerId, productId);
        return ResponseEntity.noContent().build();
    }
}
