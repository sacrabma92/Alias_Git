package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.time.LocalTime;


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
    
    @Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("Carlos", "descripcion");
        
        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
        @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("Carlos", "descripcion");
        
        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findDistinctByNameMethod(){
        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }
    
    @Test
    void findByPriceGreaterThanMethod(){
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findByPriceLessThanMethod(){
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(200));
        
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findByNameContaining(){
        List<Product> products = productRepository.findByNameContaining("product 1");
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findByNameLike(){
        List<Product> products = productRepository.findByNameLike("product 1");
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(100), new BigDecimal(300));
        
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findByDateCreatedBetweenMethod(){
        // start date -> Year - Month - Day - Hour - Minue - Second
        LocalDateTime startDate = LocalDateTime.of(2024,02,13,17,47,33);
        
        // end date -> Year - Month - Day - Hour - Minue - Second
        LocalDateTime endDate = LocalDateTime.of(2024,02,15,17,47,33);
        
        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);
        
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    
    @Test
    void findByNameInMethod(){
        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2","product 3"));
        
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
}