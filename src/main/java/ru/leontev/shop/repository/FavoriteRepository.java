package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.FavoriteEntity;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    List<FavoriteEntity> findByCustomer_Id(Long customerId);

    // Для удаления по customerId и productId
    void deleteByCustomer_IdAndProduct_Id(Long customerId, Long productId);
}
