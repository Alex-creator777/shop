package ru.leontev.shop.mapper.response;

import org.mapstruct.Mapper;
import ru.leontev.shop.dto.response.CustomerResponseDto;
import ru.leontev.shop.model.CustomerEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {
    CustomerResponseDto customerToCustomerDto(CustomerEntity customerEntity);

    List<CustomerResponseDto> customersToCustomerDtos(List<CustomerEntity> customerEntities);
}
