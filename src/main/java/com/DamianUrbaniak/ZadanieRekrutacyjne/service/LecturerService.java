package com.DamianUrbaniak.ZadanieRekrutacyjne.service;


import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.LecturerRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.StudentRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.LecturerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Lecturer> filterLecturers(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword is not provided.");
        }
        return lecturerRepository.findAll(keyword);
    }

    public Set<Lecturer> getAllLecturers() {
        return new HashSet<Lecturer>(lecturerRepository.findAll());
    }

    public Page<Lecturer> findLecturersWithSortingAndPagination(int offset, int pageSize, String field) {
        return lecturerRepository.findAll(PageRequest.of(offset, pageSize)
                .withSort(Sort.by(field)));
    }

    public Lecturer getLecturer(Long lecturerId) {
        return lecturerRepository.findLecturerById(lecturerId);
    }

    public Set<Student> getLecturerStudents(Long lecturerId) {
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

        lecturer.assignStudent(student);
        lecturerRepository.save(lecturer);
    }

    public void removeStudentfromLecturer(Long studentId, Long lecturerId) {

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            return;
        }

        Optional<Lecturer> lecturerOpt = lecturerRepository.findById(lecturerId);
        if (lecturerOpt.isEmpty()) {
            return;
        }

        Student student = studentOpt.get();
        Lecturer lecturer = lecturerOpt.get();

        Set<Student> alreadyAssigned = lecturer.getStudents();

        Set<Long> idSet = alreadyAssigned.stream()
                .map(Student::getId)
                .collect(Collectors.toSet());

        if (!idSet.contains(studentId)) {
            throw new IllegalArgumentException("Student with id " + studentId + " is not assigned to lecturer with id " + lecturerId);
        }

        lecturer.removeStudent(student);
        lecturerRepository.save(lecturer);
    }

    public void deleteLecturer(Long lecturerId) {
        boolean exists = lecturerRepository.existsById(lecturerId);
        if (!exists) {
            throw new IllegalStateException("Lecturer with id " + lecturerId + " doesn't exist!");
        }
        lecturerRepository.deleteById(lecturerId);
    }
}
