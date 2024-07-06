package com.jpa.relations.Bidirectional.Many_To_One;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bi_School_many_to_one")
@Table(name = "bi_School_many_to_one")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Student> studentList;
}
