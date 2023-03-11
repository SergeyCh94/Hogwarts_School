package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class HouseController {

    private final HouseService houseService;
    private final StudentService studentService;

    public HouseController(HouseService houseService, StudentService studentService) {
        this.houseService = houseService;
        this.studentService = studentService;
    }

    @GetMapping("/faculties")
    public List<Faculty> getAllFaculties() {
        return houseService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public Faculty getFacultyById(@PathVariable Long id){
        return houseService.findFaculty(id);
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@Valid @RequestBody Faculty faculty){
        if (faculty == null) {
            throw new IllegalArgumentException("Faculty must not be null");
        }
        Faculty createdFaculty = houseService.facultyCreate(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @Valid @RequestBody Faculty faculty){
        if (faculty.getId() == null) {
            throw new IllegalArgumentException("Faculty ID must not be null");
        }
        if (!id.equals(faculty.getId())) {
            throw new IllegalArgumentException("Faculty ID in URL and body must match");
        }
        Faculty updatedFaculty = houseService.editFaculty(faculty);
        return ResponseEntity.ok().body(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id){
        houseService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<Faculty> getFacultiesByColor(@RequestParam String color) {
        return houseService.getFacultiesByColor(color);
    }

    @GetMapping("/name")
    public List<Faculty> getByNameIgnoreCase(@RequestParam String name) {
        return houseService.findByNameIgnoreCase(name);
    }

    @GetMapping("/{id}/students")
    public List<StudentDTO> getFacultyStudents(@PathVariable Long id) {
        return studentService.getFacultyStudents(id);
    }
}