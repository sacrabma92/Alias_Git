package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(
                1,
                "Carlos",
                "Ramirez"
        );
        return student;
    }
    
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Carlos", "Ramirez"));
        students.add(new Student(2,"Difilia", "Ramirez"));
        students.add(new Student(3,"Juan", "Lopez"));
        students.add(new Student(4,"Jorge", "Ramirez"));
        return students;
    }
    
    @GetMapping("student/{id}/{firstName}/{lastName}")
    public Student studentPathVariable(@PathVariable int id,
                                       @PathVariable  String firstName,
                                       @PathVariable  String lastName){

        return new Student(id, firstName, lastName);
    }
}
