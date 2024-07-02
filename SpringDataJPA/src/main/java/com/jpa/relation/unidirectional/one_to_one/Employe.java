package com.jpa.relation.unidirectional.one_to_one;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "uni_Employe_one_to_one")
@Table(name = "uni_Employe_one_to_one")
public class Employe {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne
   @JoinColumn(name = "parking_spot_id")
   private ParkingSpot parkingSpot;
}
