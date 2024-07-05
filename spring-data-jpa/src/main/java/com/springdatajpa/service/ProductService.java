package com.springdatajpa.service;

import com.springdatajpa.Dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO, Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> saveAllProducts(List<ProductDTO> products);

    List<ProductDTO> getAllProducts();

    void deleteProductById(Long id);

    void deleteAllProducts();

    long countProducts(); // Método para contar los productos

    boolean existsProductById(Long id); // Método para verificar si un producto existe por ID

    void disableProductById(Long id); // Método para desactivar un producto por ID

    void enableProductById(Long id); // Método para activar un producto por ID

    List<ProductDTO> findAllEnabledProducts(); // Método para encontrar todos los productos habilitados
}
