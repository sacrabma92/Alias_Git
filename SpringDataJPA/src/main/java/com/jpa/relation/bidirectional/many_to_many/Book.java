package com.jpa.relation.bidirectional.many_to_many;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bid_Book_many_to_many")
@Table(name = "bid_Book_many_to_many")
public class Book {   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToMany
   @JoinTable(name = "author_book_biddirectional",
           joinColumns = @JoinColumn(name = "book_id"),
   inverseJoinColumns = @JoinColumn(name = "author_id"))
   private List<Author> authorsList;

}
