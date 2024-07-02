package com.jpa.relation.bidirectional.many_to_many;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dib_Author_many_to_many")
@Table(name = "bid_Author_many_to_many")
public class Author {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToMany(mappedBy = "authorsList")
   private List<Book> bookList;
}
