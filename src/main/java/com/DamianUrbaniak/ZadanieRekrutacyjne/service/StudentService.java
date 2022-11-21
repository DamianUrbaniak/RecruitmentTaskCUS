package com.DamianUrbaniak.ZadanieRekrutacyjne.service;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.LecturerRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.repository.StudentRepository;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long studentId) {
        return studentRepository.findStudentById(studentId);
    }

    public List<Lecturer> getStudentLecturers(Long studentId) {
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

        student.getLecturers().add(lecturer);
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

