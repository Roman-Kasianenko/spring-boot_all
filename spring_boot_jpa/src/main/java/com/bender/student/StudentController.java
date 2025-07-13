package com.bender.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<StudentResponse> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(s -> new StudentResponse(
                        s.getId(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getBooks(),
                        s.getCourseEnrollments(),
                        s.getCreatedAt()
                ))
                .toList();
    }
}
