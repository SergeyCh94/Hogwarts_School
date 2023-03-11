package ru.hogwarts.school.dto;

import ru.hogwarts.school.model.Student;

public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private Long facultyId;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        if (student.getFaculty() != null) {
            this.facultyId = student.getFaculty().getId();
        }
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}
