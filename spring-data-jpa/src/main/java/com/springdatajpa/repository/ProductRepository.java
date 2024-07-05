package com.springdatajpa.repository;

import com.springdatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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

    List<Product> findByPriceLessThan(Double price); // Método personalizado para encontrar productos con precio menor que un valor dado

    List<Product> findByDescriptionContaining(String description); // Método personalizado para encontrar productos cuya descripción contenga una cadena dada

    // Método personalizado para encontrar productos cuya descripción contenga una cadena dada usando LIKE
    List<Product> findByDescriptionLike(String description);

    // Método personalizado para encontrar productos cuyo precio esté dentro de un rango específico
    List<Product> findByPriceBetween(Double startPrice, Double endPrice);

    // Método personalizado para encontrar productos cuya fecha esté dentro de un rango específico
    List<Product> findByDateCreatedBetween(Date startDate, Date endDate);

    @Query("SELECT p FROM Product p WHERE p.name = ?1 or p.description = ?2")
    Product findByNameAndDescription(@Param("name") String name, @Param("description") String description);

    @Query(
            value = "SELECT * FROM products p WHERE p.name LIKE %:name% AND p.description LIKE %:description%",
            nativeQuery = true
    )
    List<Product> searchByNameAndDescriptionNative( @Param("name") String name, @Param("description") String description);
}
