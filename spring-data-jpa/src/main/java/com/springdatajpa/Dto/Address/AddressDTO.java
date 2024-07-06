package com.springdatajpa.Dto.Address;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String street;
    private String state;
    private String country;
    private String zipCode;
}
