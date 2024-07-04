package com.springdatajpa.controller;

import com.springdatajpa.Dto.ProductDTO;
import com.springdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CreatePost
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,@PathVariable(name = "id") Long id){
        ProductDTO productResponse = productService.updateProduct(productDTO, id);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
}
