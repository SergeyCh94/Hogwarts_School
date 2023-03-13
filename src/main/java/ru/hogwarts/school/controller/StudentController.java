package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;
import javax.validation.Valid;
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
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO, UriComponentsBuilder uriBuilder) {
        StudentDTO createdStudent = studentService.studentCreate(studentDTO);
        return ResponseEntity
                .created(uriBuilder.path("/student/{id}").buildAndExpand(createdStudent.getId()).toUri())
                .body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.editStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<StudentDTO> getStudentsByAge(@RequestParam(required = true) Integer age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/age")
    public List<StudentDTO> findByAgeBetween(@RequestParam(required = true) Integer minAge, @RequestParam(required = true) Integer maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<FacultyDTO> getStudentFaculty(@PathVariable Long id) {
        FacultyDTO facultyDTO = studentService.getStudentFaculty(id);
        if (facultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }
}
