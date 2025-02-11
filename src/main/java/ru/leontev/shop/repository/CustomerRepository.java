package ru.leontev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leontev.shop.model.CategoryEntity;
import ru.leontev.shop.model.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
