package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long idCounter = 0L;

    public Student studentCreate(Student student){
        ++idCounter;
        student.setId(idCounter);
        students.put(idCounter, student);
        return student;
    }

    public Student findStudent(long id){
        return students.get(id);
    }

    public Student editStudent(Student student){
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id){
        return students.remove(id);
    }

    public List<Student> getStudentsByAge(int age) {
        List<Student> students = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() == age) {
                students.add(student);
            }
        }
        return students;
    }
}
