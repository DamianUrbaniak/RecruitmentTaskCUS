package com.DamianUrbaniak.ZadanieRekrutacyjne.controller;


import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.service.StudentService;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.saveStudent(studentDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/assignLecturer/{lecturerId}")
    public void assignLecturerToStudent(@PathVariable("studentId") Long studentId, @PathVariable("lecturerId") Long lecturerId) {
        studentService.assignLecturerToStudent(studentId, lecturerId);
    }

    @GetMapping("/studentLecturers/{studentId}")
    public ResponseEntity<List<Lecturer>> getStudentLecturers(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok(studentService.getStudentLecturers(studentId));
    }

    @GetMapping("/getAllStudents/{keyword}")
    public ResponseEntity<List<Student>> getAllStudents(@PathVariable String keyword) {
        return ResponseEntity.ok(studentService.getAllStudents(keyword));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}


