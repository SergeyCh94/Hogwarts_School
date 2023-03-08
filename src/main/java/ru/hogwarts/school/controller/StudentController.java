package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.studentCreate(student);
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestBody Student student){
        return studentService.editStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student")
    public List<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }
}
