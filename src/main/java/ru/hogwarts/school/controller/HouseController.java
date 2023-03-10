package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class HouseController {
    @Autowired
    private final HouseService houseService;

    @Autowired
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
    public Faculty createFaculty(@RequestBody Faculty faculty){
        if (faculty == null) {
            throw new IllegalArgumentException("Student must not be null");
        }
        return houseService.facultyCreate(faculty);
    }

    @PutMapping()
    public Faculty updateFaculty(@RequestBody Faculty faculty){
        return houseService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id){
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
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
