package com.DamianUrbaniak.ZadanieRekrutacyjne.repository;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Lecturer findLecturerById(Long lecturerId);

}
