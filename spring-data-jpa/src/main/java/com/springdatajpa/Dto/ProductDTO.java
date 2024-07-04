package com.springdatajpa.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private long id;
    private String sku;

    @NotEmpty
    @Size(min = 2, message = "El nombre debe tener mas de 2 caracteres")
    private String name;

    @NotEmpty
    @Size(min = 8, message = "La descripcion debe tener mas de 8 caracteres")
    private String description;
    private BigDecimal price;
    private Boolean active;

    @NotEmpty(message = "No puede estar vacio")
    private String imageUrl;

}
