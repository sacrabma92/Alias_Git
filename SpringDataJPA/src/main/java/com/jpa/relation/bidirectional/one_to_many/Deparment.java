package com.jpa.relation.bidirectional.one_to_many;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bid_Deparment_one_to_many")
@Table(name = "bid_Deparment_one_to_many")
public class Deparment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany(mappedBy = "deparment")
   private List<Employe> employes;
}
