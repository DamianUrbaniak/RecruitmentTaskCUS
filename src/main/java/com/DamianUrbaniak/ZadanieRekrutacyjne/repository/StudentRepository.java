package com.DamianUrbaniak.ZadanieRekrutacyjne.repository;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentById(Long studentId);
}
