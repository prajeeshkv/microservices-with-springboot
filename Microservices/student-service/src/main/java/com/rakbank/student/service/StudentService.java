package com.rakbank.student.service;

import com.rakbank.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    List<Student> findAll();
    List<Student> findAllActive();
    List<Student> findAllInActive();
    Optional<Student> findById(Long id);
    Optional<Student> findByStudentId(String studentId);
    boolean existsById(Long id);
    void deleteById(Long id);
    Student deleteStudent(Student student);
}