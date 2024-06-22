package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Carlos",
                "Ramirez"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok(student);
    }
    
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Carlos", "Ramirez"));
        students.add(new Student(2,"Difilia", "Ramirez"));
        students.add(new Student(3,"Juan", "Lopez"));
        students.add(new Student(4,"Jorge", "Ramirez"));
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
    
    @GetMapping("student/{id}/{firstName}/{lastName}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id,
                                                       @PathVariable  String firstName,
                                                       @PathVariable  String lastName){
        Student student = new  Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("student/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id){
        System.out.println(student.getLastName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<String> delteStudent(@PathVariable int id){
        System.out.println(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
