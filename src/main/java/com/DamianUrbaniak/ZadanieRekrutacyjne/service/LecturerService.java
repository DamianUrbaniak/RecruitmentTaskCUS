package com.DamianUrbaniak.ZadanieRekrutacyjne.service;


import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }
}
