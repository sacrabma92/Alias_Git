package com.springdatajpa.repository;

import com.springdatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);

    List<Product> findByActiveTrue();

    Optional<Product> findByName(String name); // Método personalizado para encontrar un producto por nombre

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);// Método personalizado
    // para encontrar productos por nombre o descripción

    List<Product> findDistinctByName(String name); // Método personalizado para encontrar productos con nombres distintos

    List<Product> findByPriceGreaterThan(Double price); // Método personalizado para encontrar productos con precio mayor que un valor dado
}
