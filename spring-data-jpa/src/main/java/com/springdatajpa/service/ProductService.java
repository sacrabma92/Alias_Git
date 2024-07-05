package com.springdatajpa.service;

import com.springdatajpa.Dto.ProductDTO;
import java.util.List;
import java.util.Optional;

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

    Optional<ProductDTO> findProductByName(String name); // Método para encontrar un producto por nombre

    List<ProductDTO> findProductsByNameOrDescription(String search); // Metodo para buscar por nombre o descripcion

    List<ProductDTO> findDistinctProductsByName(String name); // Método para encontrar productos con nombres distintos

    List<ProductDTO> findProductsByPriceGreaterThan(Double price); // Método para encontrar productos con precio mayor que un valor dado
}
