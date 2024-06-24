package com.curso.api.spring_security_course.controller;

import com.curso.api.spring_security_course.dto.SaveProduct;
import com.curso.api.spring_security_course.persistence.entity.Category;
import com.curso.api.spring_security_course.persistence.entity.Product;
import com.curso.api.spring_security_course.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
   @Autowired
   private CategoryService categoryService;

   @GetMapping
   public ResponseEntity<Page<Category>> findAll(Pageable pageable){
      Page<Category> category = categoryService.findAll(pageable);

      if(category.hasContent()){
         return ResponseEntity.ok(category);
      }

      return ResponseEntity.notFound().build();
   }

   @GetMapping("/categoryId")
   public ResponseEntity<Category> findByOneId(@PathVariable Long categoryId){
      Optional<Category> category = categoryService.findOneById(categoryId);

      if(category.isPresent()){
         return ResponseEntity.ok(category.get());
      }
      return ResponseEntity.notFound().build();
   }

   @PostMapping
   public ResponseEntity<Category> createOne(@RequestBody @Valid SaveCategory saveProduct){
      Category category = categoryService.createOne(saveProduct);
      return ResponseEntity.status(HttpStatus.CREATED).body(category);
   }

   @PutMapping("/{categoryId}")
   public ResponseEntity<Category> updateOneById(@PathVariable Long categoryId,
                                                @RequestBody @Valid SaveProduct saveProduct){
      Category category = categoryService.updateOneById(categoryId,saveProduct);
      return ResponseEntity.ok(category);
   }

   @PutMapping("{categoryId}/disabled")
   public ResponseEntity<Category> disableOneById(@PathVariable Long categoryId){
      Category product = categoryService.disableOneById(categoryId);
      return ResponseEntity.ok(product);
   }
}
