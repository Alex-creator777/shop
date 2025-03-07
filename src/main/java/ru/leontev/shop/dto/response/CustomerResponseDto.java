package ru.leontev.shop.dto.response;

import lombok.Data;

@Data
public class CustomerResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
