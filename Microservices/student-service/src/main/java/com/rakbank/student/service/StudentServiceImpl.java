package com.rakbank.student.service;


import com.rakbank.student.constants.ApplicationConstants;
import com.rakbank.student.enums.SchoolNames;
import com.rakbank.student.exception.custom.EntityNotFoundException;
import com.rakbank.student.model.Student;
import com.rakbank.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        student.setSchoolName(SchoolNames.getValueByKey(ApplicationConstants.SCHOOL_NAME_SKIPLY));
        student.setStatus(ApplicationConstants.TRUE); // true while saving student
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return saveStudent(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllActive() {
        return studentRepository.findByStatusTrue();
    }

    @Override
    public List<Student> findAllInActive() {
        return studentRepository.findByStatusFalse();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.of(studentRepository.findById(id)).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Optional<Student> findByStudentId(String studentId) {

        return studentRepository.findByStudId(studentId);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student deleteStudent(Student student) {
        student.setStatus(ApplicationConstants.FALSE); // true while saving student
        return studentRepository.save(student);
    }


}
