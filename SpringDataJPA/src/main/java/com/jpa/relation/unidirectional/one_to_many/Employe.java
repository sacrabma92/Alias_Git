package com.jpa.relation.unidirectional.one_to_many;
import com.jpa.relation.unidirectional.one_to_one.ParkingSpot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "uni_Employe_one_to_many")
@Table(name = "uni_Employe_one_to_many")
public class Employe {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

}