package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /*
    Returns the found product entry by using its name as search criteria.
    If NO product entry is found, this method return NULL.
    */
    public Product findByName(String name);
    
    /*
    Return an Optional which contains the found product entry using its id
    as search criteria. If NO product entry is found, this method return an empty
    Optional.
    */
    Optional<Product> findById(int id);
    
}
