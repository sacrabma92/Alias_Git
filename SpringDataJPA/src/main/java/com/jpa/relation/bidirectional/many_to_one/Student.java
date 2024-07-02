package com.jpa.relation.bidirectional.many_to_one;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bid_Student_many_to_one")
@Table(name = "bid_Student_many_to_one")
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne()
   @JoinColumn(name = "school_id")
   private School school;
}
