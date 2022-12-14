package com.DamianUrbaniak.ZadanieRekrutacyjne.repository;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.name LIKE %?1%"
                                + " OR s.lastName LIKE %?1%")
    public List<Student> findAll(String keyword);

    Student findStudentById(Long studentId);
}
