package ru.hogwarts.school.controller;


import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/faculty/{id}")
    public Faculty getFacultyById(@PathVariable Long id){
        return houseService.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(Faculty faculty){
        return houseService.facultyCreate(faculty);
    }

    @PutMapping("/faculty")
    public Faculty updateFaculty(Faculty faculty){
        return houseService.editFaculty(faculty);
    }

    @DeleteMapping("/faculty/{id}")
    public void deleteFaculty(@PathVariable Long id){
        houseService.deleteFaculty(id);
    }

    @GetMapping("/faculty")
    public List<Faculty> getFacultiesByColor(@RequestParam String color) {
        return houseService.getFacultiesByColor(color);
    }
}
