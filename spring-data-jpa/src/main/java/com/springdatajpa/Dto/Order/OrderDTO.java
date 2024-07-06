package com.springdatajpa.Dto.Order;

import com.springdatajpa.Dto.Address.AddressDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    private AddressDTO address;
}
