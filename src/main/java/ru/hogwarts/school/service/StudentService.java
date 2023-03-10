package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;
    private StudentDTO studentDTO;

    public StudentDTO studentCreate(StudentDTO studentDTO) {
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + studentDTO.getFacultyId()));
        Student student = new Student(studentDTO.getName(), studentDTO.getAge(), faculty);
        student = studentRepository.save(student);
        return new StudentDTO(student);
    }

    public StudentDTO findStudent(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + studentDTO.getFacultyId()));
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setFaculty(faculty);
        student = studentRepository.save(student);
        return new StudentDTO(student);
    }

    public StudentDTO editStudent(Long id, StudentDTO studentDTO){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + studentDTO.getFacultyId()));
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setFaculty(faculty);
        student = studentRepository.save(student);
        return new StudentDTO(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    public List<StudentDTO> getStudentsByAge(int age) {
        List<Student> students = studentRepository.findByAge(age);
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(new StudentDTO(student));
        }
        return studentDTOs;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(new StudentDTO(student));
        }
        return studentDTOs;
    }

    public List<StudentDTO> findByAgeBetween(int minAge, int maxAge) {
        List<Student> students = studentRepository.findByAgeBetween(minAge, maxAge);
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(new StudentDTO(student));
        }
        return studentDTOs;
    }

    public FacultyDTO getStudentFaculty(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        Faculty faculty = student.getFaculty();
        if (faculty == null) {
            throw new EntityNotFoundException("Faculty not found for student with id: " + id);
        }
        return new FacultyDTO(faculty);
    }

    public List<StudentDTO> getFacultyStudents(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + id));
        List<Student> students = faculty.getStudents();
        return students.stream().map(StudentDTO::new).collect(Collectors.toList());
    }
}
