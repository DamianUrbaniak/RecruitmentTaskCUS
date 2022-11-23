package com.DamianUrbaniak.ZadanieRekrutacyjne.service;


import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.LecturerDTO;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.LecturerRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private StudentRepository studentRepository;


    public Lecturer saveLecturer(LecturerDTO lecturerDTO) {

        Lecturer lecturer = Lecturer
                .build(0L, lecturerDTO.getName(), lecturerDTO.getLastName(), lecturerDTO.getAge(),
                        lecturerDTO.getDateOfBirth(), lecturerDTO.getEmail(), lecturerDTO.getSubject());

        return lecturerRepository.save(lecturer);
    }

    public List<Lecturer> filterLecturers(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword is not provided.");
        }
        return lecturerRepository.findAll(keyword);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Page<Lecturer> findLecturersWithSortingAndPagination(int offset, int pageSize, String field) {
        return lecturerRepository.findAll(PageRequest.of(offset, pageSize)
                .withSort(Sort.by(field)));
    }

    public Lecturer getLecturer(Long lecturerId) {
        return lecturerRepository.findLecturerById(lecturerId);
    }

    public List<Student> getLecturerStudents(Long lecturerId) {
        return getLecturer(lecturerId).getStudents();
    }

    public void deleteLecturer(Long lecturerId) {
        boolean exists = lecturerRepository.existsById(lecturerId);
        if (!exists) {
            throw new IllegalStateException("Lecturer with id " + lecturerId + " doesn't exist!");
        }
        lecturerRepository.deleteById(lecturerId);
    }
}
