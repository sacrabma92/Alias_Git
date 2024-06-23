package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /*
    Devuelve la entrada de producto encontrada utilizando su nombre como criterio de búsqueda.
    Si NO se encuentra ninguna entrada de producto, este método devuelve NULL.
    */
    public Product findByName(String name);
    
    /*
    Devuelve un Optional que contiene la entrada de producto encontrada usando su id
    como criterio de búsqueda. Si NO se encuentra ninguna entrada de producto, este método devuelve un
    vacío.
    */
    Optional<Product> findById(int id);
    
    /*
    Devuelve la lista encontrada de entradas de productos cuyo título o descripción se da
    como parámetro del método. Si no se encuentra ninguna entrada de producto, este método devuelve 
    una lista vacía
    */
    List<Product> findByNameOrDescription(String name, String description);
    
    /*
    Devuelve la lista encontrada de entradas de productos cuyo título y descripción se dan
    como parámetro del método. Si no se encuentra ninguna entrada de producto, este método devuelve 
    una lista vacía
    */
    List<Product> findByNameAndDescription(String name, String description);
    
    /*
    Devuelve la entrada de producto distict cuyo nombre se da como parámetro del método.
    Si no se encuentra ninguna entrada de producto, este método devuelve null.
    */
    Product findDistinctByName(String name);
    
    /*
    Devuelve el producto cuyo precio es mayor que el precio dado como parámetro del método
    @param
    @return
    */
    List<Product> findByPriceGreaterThan(BigDecimal price);
    
    /*
    Devuelve el producto cuyo precio es menor que el precio dado como parámetro del método
    @param price
    @return
    */
    List<Product> findByPriceLessThan(BigDecimal price);
    
    /*
    Devuelve los registros de productos filtrados que coinciden con el texto dado
    @param price
    @return
    */
    List<Product> findByNameContaining(String name);
    
    /*
    Devolución de productos basada en la condición SQL like
    @param name
    @return
    */
    List<Product> findByNameLike(String name);
    
    /*
    Devuelve los productos cuyo precio está comprendido entre el precio inicial y el precio final
    @param startPrice
    @param endPrice
    @return
    */
    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
    
    /*
    Devuelve los productos cuya fecha está comprendida entre la fecha inicial y la fecha final
    @param startDate
    @param endDate
    @return
    */
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /*
    Devuelve una lista de productos basada en varios valores
    @param names
    @return
    */
    List<Product> findByNameIn(List<String> names);
    
    List<Product> findFirst2ByOrderByNameAsc();
}
