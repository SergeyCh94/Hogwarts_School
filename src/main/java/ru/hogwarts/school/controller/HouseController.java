package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class HouseController {
    @Autowired
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/{id}")
    public Faculty getFacultyById(@PathVariable Long id){
        return houseService.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty){
        return houseService.facultyCreate(faculty);
    }

    @PutMapping("/")
    public Faculty updateFaculty(@RequestBody Faculty faculty){
        return houseService.editFaculty(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id){
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<Faculty> getFacultiesByColor(@RequestParam String color) {
        return houseService.getFacultiesByColor(color);
    }
}
