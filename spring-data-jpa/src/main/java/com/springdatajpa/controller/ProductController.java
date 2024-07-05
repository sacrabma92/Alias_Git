package com.springdatajpa.controller;

import com.springdatajpa.Dto.ProductDTO;
import com.springdatajpa.service.ProductService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    // Obtener todos los productos
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable(name = "id") Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>("Entidad Product eliminada con exito", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllProducts() {
        productService.deleteAllProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countProducts() {
        long count = productService.countProducts();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsProductById(@PathVariable Long id) {
        boolean exists = productService.existsProductById(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<Void> disableProductById(@PathVariable Long id) {
        productService.disableProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<Void> enableProductById(@PathVariable Long id) {
        productService.enableProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/enabled")
    public ResponseEntity<List<ProductDTO>> getAllEnabledProducts() {
        List<ProductDTO> enabledProducts = productService.findAllEnabledProducts();
        return new ResponseEntity<>(enabledProducts, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name) {
        Optional<ProductDTO> product = productService.findProductByName(name);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> getProductByNameOrDescription(@RequestParam String search){
        List<ProductDTO> products = productService.findProductsByNameOrDescription(search);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/distinct")
    public ResponseEntity<List<ProductDTO>> getDistinctProductsByName(@RequestParam String name) {
        List<ProductDTO> products = productService.findDistinctProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/precio-mayor-que")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceGreaterThan(@RequestParam Double price) {
        List<ProductDTO> products = productService.findProductsByPriceGreaterThan(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/precio-menor-que")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceLessThan(@RequestParam Double price) {
        List<ProductDTO> products = productService.findProductsByPriceLessThan(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/description-containing")
    public ResponseEntity<List<ProductDTO>> getProductsByDescriptionContaining(@RequestParam String description) {
        List<ProductDTO> products = productService.findProductsByDescriptionContaining(description);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/description-like")
    public ResponseEntity<List<ProductDTO>> getProductsByDescriptionLike(@RequestParam String description) {
        List<ProductDTO> products = productService.findProductsByDescriptionLike(description);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceRange(@RequestParam Double startPrice, @RequestParam Double endPrice) {
        List<ProductDTO> products = productService.findProductsByPriceBetween(startPrice, endPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ProductDTO>> getProductsByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<ProductDTO> products = productService.findProductsByDateBetween(startDate, endDate);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
