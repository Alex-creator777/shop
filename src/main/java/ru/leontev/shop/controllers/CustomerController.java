package ru.leontev.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "получение списка всех заказчиков")
       @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Operation(summary = "получение заказчика по id")
    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
}
