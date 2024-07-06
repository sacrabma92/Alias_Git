package com.jpa.relations.Bidirectional.Many_To_Many;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bi_Author_many_to_many")
@Table(name = "bi_Author_many_to_many")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "authorList")
    private List<Book> bookList;
}
