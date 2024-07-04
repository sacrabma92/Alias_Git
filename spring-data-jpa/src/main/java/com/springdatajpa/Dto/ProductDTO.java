package com.springdatajpa.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private long id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private String imageUrl;

}
