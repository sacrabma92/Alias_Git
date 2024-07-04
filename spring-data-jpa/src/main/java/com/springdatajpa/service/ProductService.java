package com.springdatajpa.service;

import com.springdatajpa.Dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO, Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> saveAllProducts(List<ProductDTO> products);
}
