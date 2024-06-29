package com.jpa.SpringJpaRelation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FotballCompetition {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", columnDefinition = "VARCHAR(120)")
   private String name;

   @Column(name = "cuantity_price", nullable = false)
   private Integer cuantityPrice;

   @Column(name = "start_date", columnDefinition = "DATE")
   private LocalDate startDate;

   @Column(name = "end_date", columnDefinition = "DATE")
   private LocalDate endDate;

   @ManyToMany(targetEntity = Club.class, fetch = FetchType.LAZY)
   @JoinTable(name = "club_competitions",
           joinColumns = @JoinColumn(name = "id_fotball_competition"),
           inverseJoinColumns = @JoinColumn(name = "id_club"))
   private List<Club> clubs;
}
