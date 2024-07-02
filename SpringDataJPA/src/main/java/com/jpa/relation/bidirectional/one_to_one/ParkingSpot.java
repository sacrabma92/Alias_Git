package com.jpa.relation.bidirectional.one_to_one;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bid_ParkingSpot_one_to_one")
@Table(name = "bid_ParkingSpot_one_to_one")
public class ParkingSpot {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(mappedBy = "parkingSpot")
   private Employe employe;
}
