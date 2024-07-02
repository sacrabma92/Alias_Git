package com.jpa.relation.unidirectional.one_to_many;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "uni_Deparment_one_to_many")
@Table(name = "uni_Deparment_one_to_many")
public class Deparment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToMany
   @JoinColumn(name = "department_id")
   private List<Employe> employes;
}
