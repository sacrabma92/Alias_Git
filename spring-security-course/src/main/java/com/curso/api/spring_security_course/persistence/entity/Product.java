package com.curso.api.spring_security_course.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nombre;
   private BigDecimal price;
   @Enumerated(EnumType.STRING)
   private ProductStatus status;

   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;

   public static enum ProductStatus{
      ENABLED, DISABLED;
   }

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public ProductStatus getStatus() {
      return status;
   }

   public void setStatus(ProductStatus status) {
      this.status = status;
   }
}
