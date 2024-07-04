package com.springdatajpa.controller;

import com.springdatajpa.Dto.ProductDTO;
import com.springdatajpa.service.ProductService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CreatePost
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,@PathVariable(name = "id") Long id){
        ProductDTO productResponse = productService.updateProduct(productDTO, id);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    // Obtener producto Por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Guardamos varios productos al mismo tiempo.
    @PostMapping("/saveAll")
    public ResponseEntity<List<ProductDTO>> saveAll(@RequestBody List<ProductDTO> productsDTO) {
        List<ProductDTO> savedProducts = productService.saveAllProducts(productsDTO);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }
}
