package com.DamianUrbaniak.ZadanieRekrutacyjne.repository;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    @Query("SELECT l FROM Lecturer l WHERE l.name LIKE %?1%"
                                + " OR l.lastName LIKE %?1%")
    public Set<Lecturer> findAll(String keyword);
    Lecturer findLecturerById(Long lecturerId);



}
