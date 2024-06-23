package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        // Crear el producto
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        // save product
        Product saveObject = productRepository.save(product);

        // Display product info
        System.out.println(saveObject.getId());
        System.out.println(saveObject.toString());
    }
    
    @Test
    void updateUsingSaveMethod(){
        // find or retrive an entity by id
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        
        // save updated entity
        product.setName("updated product 1");
        product.setDescription("updated product 1 desc");
        
        // save updated entity
        productRepository.save(product);
    }
    
    @Test
    void findByIdMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }
    
    @Test
    void findByNameMethod(){
        Product product = productRepository.findByName("product 1");
        
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }
    
    @Test
    void findByIdMethod2(){
        Product product = productRepository.findById(1L).get();
        System.out.println(product.getId());
    }
}