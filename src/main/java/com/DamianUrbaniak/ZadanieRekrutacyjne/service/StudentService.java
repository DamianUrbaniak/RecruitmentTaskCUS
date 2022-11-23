package com.DamianUrbaniak.ZadanieRekrutacyjne.service;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.LecturerRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.StudentRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.StudentDTO;
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
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LecturerRepository lecturerRepository;

    public Student saveStudent(StudentDTO studentDTO) {

        Student student = Student
                .build(0L, studentDTO.getName(), studentDTO.getLastName(), studentDTO.getAge(),
                        studentDTO.getDateOfBirth(), studentDTO.getEmail(), studentDTO.getField());

        return studentRepository.save(student);
    }

    public Set<Student> filterStudents(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword is not provided.");
        }
        return studentRepository.findAll(keyword);
    }

    public Set<Student> getAllStudents() {
        return new HashSet<Student>(studentRepository.findAll());
    }

    public Page<Student> findStudentsWithSortingAndPagination(int offset, int pageSize, String field) {
        Page<Student> students = studentRepository
                .findAll(PageRequest.of(offset, pageSize)
                        .withSort(Sort.by(field)));
        return students;
    }

    public Student getStudent(Long studentId) {
        return studentRepository.findStudentById(studentId);
    }

    public Set<Lecturer> getStudentLecturers(Long studentId) {
        return getStudent(studentId).getLecturers();
    }

    public void assignLecturerToStudent(Long studentId, Long lecturerId) {

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

        Set<Lecturer> alreadyAssigned = student.getLecturers();

        Set<Long> idSet = alreadyAssigned.stream()
                .map(Lecturer::getId)
                .collect(Collectors.toSet());

        if (idSet.contains(lecturerId)) {
            throw new IllegalArgumentException("Lecturer is already assigned.");
        }

        student.assignLecturer(lecturer);
        studentRepository.save(student);
    }

    public void removeLecturerToStudent(Long studentId, Long lecturerId) {

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

        Set<Lecturer> alreadyAssigned = student.getLecturers();

        Set<Long> idSet = alreadyAssigned.stream()
                .map(Lecturer::getId)
                .collect(Collectors.toSet());

        if (!idSet.contains(lecturerId)) {
            throw new IllegalArgumentException("Lecturer with id " + lecturerId + " is not assigned to student with id " + studentId);
        }

        student.removeLecturer(lecturer);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " doesn't exist!");
        }
        studentRepository.deleteById(studentId);
    }
}

