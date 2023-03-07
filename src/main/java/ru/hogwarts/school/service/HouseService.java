package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseService {
    private Map<Long, Faculty> faculties = new HashMap<Long, Faculty>();
    private Long idCounter = 0L;

    public Faculty facultyCreate(Faculty faculty){
        ++idCounter;
        faculty.setId(idCounter);
        faculties.put(idCounter, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id){
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty){
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id){
        return faculties.remove(id);
    }

    public List<Faculty> getFacultiesByColor(String color) {
        List<Faculty> faculties = new ArrayList<>();
        for (Faculty faculty : faculties) {
            if (faculty.getColor().equals(color)) {
                faculties.add(faculty);
            }
        }
        return faculties;
    }
}
