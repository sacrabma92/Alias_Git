package com.jpa.SpringJpaRelation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FotballAssociation {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String country;
   private String president;

   @OneToMany(mappedBy = "fotballAssociation", targetEntity = Club.class, fetch = FetchType.LAZY)
   private List<Club> club;
}
