package com.springdatajpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "products",
         schema = "ecommerce",
         uniqueConstraints = {
            @UniqueConstraint(
                    name = "sku_uni",
                    columnNames = "stock_keeping_unit"
            )
         })
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "stock_keeping_unit", nullable = false)
   private String sku;


   private String name;


   private String description;

   private BigDecimal price;

   private boolean active;

   private String imageUrl;

   @CreationTimestamp
   private LocalDateTime dateCreated;

   @UpdateTimestamp
   private LocalDateTime lastUpdated;

}
