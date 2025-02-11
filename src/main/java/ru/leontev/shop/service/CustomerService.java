package ru.leontev.shop.service;

import org.springframework.stereotype.Service;
import ru.leontev.shop.dto.response.CustomerResponseDto;
import ru.leontev.shop.mapper.response.CustomerResponseMapper;
import ru.leontev.shop.model.CustomerEntity;
import ru.leontev.shop.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerResponseMapper customerResponseMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerResponseMapper customerResponseMapper) {
        this.customerRepository = customerRepository;
        this.customerResponseMapper = customerResponseMapper;
    }

    public List<CustomerResponseDto> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerResponseMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerResponseDto getCustomerById(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerResponseMapper.customerToCustomerDto(customer);
    }
}
