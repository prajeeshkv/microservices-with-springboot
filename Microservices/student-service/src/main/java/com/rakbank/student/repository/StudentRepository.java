package com.rakbank.student.repository;

import com.rakbank.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudId(String id);
    List<Student> findByStatusTrue();
    List<Student> findByStatusFalse();
}
