package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        if (studentDTO == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        return studentService.studentCreate(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.editStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<StudentDTO> getStudentsByAge(@RequestParam Integer age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/age")
    public List<StudentDTO> findByAgeBetween(@RequestParam Integer minAge, Integer maxAge) {
        if (minAge == null || maxAge == null) {
            throw new IllegalArgumentException("minAge and maxAge must not be null");
        }
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{id}/faculty")
    public FacultyDTO getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentFaculty(id);
    }
}
