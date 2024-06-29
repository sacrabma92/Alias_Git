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
public class Club {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @OneToOne(targetEntity = Coach.class, cascade = CascadeType.PERSIST)
   @JoinColumn(name = "id_coach")
   private Coach coach;

   @OneToMany(mappedBy = "club", targetEntity = Player.class, fetch = FetchType.LAZY)
   private List<Player> player;

   @ManyToOne
   private FotballAssociation fotballAssociation;

}
