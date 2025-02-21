package ru.leontev.shop.service.serviceResponse;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.FavoriteResponseDto;
import ru.leontev.shop.mapper.response.FavoriteResponseMapper;
import ru.leontev.shop.model.FavoriteEntity;
import ru.leontev.shop.repository.FavoriteRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceResponse {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteResponseMapper favoriteResponseMapper;

    public FavoriteServiceResponse(FavoriteRepository favoriteRepository, FavoriteResponseMapper favoriteResponseMapper) {
        this.favoriteRepository = favoriteRepository;
        this.favoriteResponseMapper = favoriteResponseMapper;
    }

    public List<FavoriteResponseDto> getFavoritesByCustomer(Long customerId) {
        List<FavoriteEntity> favorites = favoriteRepository.findByCustomer_Id(customerId);
        return favorites.stream()
                .map(favoriteResponseMapper::favoriteEntityToFavoriteResponseDto)
                .collect(Collectors.toList());
    }
}
