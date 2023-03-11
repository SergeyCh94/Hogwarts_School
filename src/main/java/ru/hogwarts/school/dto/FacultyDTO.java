package ru.hogwarts.school.dto;

import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyDTO {
    private Long id;
    private String name;
    private List<StudentDTO> students = new ArrayList<>();

    public FacultyDTO(Long id, String name, List<StudentDTO> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public FacultyDTO(Faculty faculty) {
        this.id = faculty.getId();
        this.name = faculty.getName();
        this.students = faculty.getStudents().stream().map(StudentDTO::new).collect(Collectors.toList());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
