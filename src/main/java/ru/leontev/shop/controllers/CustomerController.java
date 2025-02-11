package ru.leontev.shop.controllers;

import org.springframework.web.bind.annotation.*;
import ru.leontev.shop.dto.response.CustomerResponseDto;
import ru.leontev.shop.service.CustomerService;


import java.util.List;

@RestController
@RequestMapping("/customer") // Базовый URL для всех методов контроллера
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

       @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
}
