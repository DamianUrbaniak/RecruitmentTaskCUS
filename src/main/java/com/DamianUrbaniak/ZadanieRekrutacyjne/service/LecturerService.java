package com.DamianUrbaniak.ZadanieRekrutacyjne.service;


import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.LecturerRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.StudentRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.LecturerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturer(Long lecturerId) {
        return lecturerRepository.findLecturerById(lecturerId);
    }

    public List<Student> getLecturerStudents(Long lecturerId) {
        return getLecturer(lecturerId).getStudents();
    }

    public void assignStudentToLecturer(Long lecturerId, Long studentId) {

        Optional<Lecturer> lecturerOpt = lecturerRepository.findById(lecturerId);
        if (lecturerOpt.isEmpty()) {
            return;
        }

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            return;
        }

        Lecturer lecturer = lecturerOpt.get();
        Student student = studentOpt.get();


        lecturer.getStudents().add(student);
        lecturerRepository.save(lecturer);
    }

//    public Lecturer getLecturerByName(String name) {
//        return lecturerRepository.findLecturerByName(name);
//    }

    public void deleteLecturer(Long lecturerId) {
        boolean exists = lecturerRepository.existsById(lecturerId);
        if (!exists) {
            throw new IllegalStateException("Lecturer with id " + lecturerId + " doesn't exist!");
        }
        lecturerRepository.deleteById(lecturerId);
    }
}
