package com.DamianUrbaniak.ZadanieRekrutacyjne.controller;

import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.APIResponse;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.StudentDTO;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Student;
import com.DamianUrbaniak.ZadanieRekrutacyjne.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Student>> getStudentsWithSortingAndPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Student> studentsWithSortingAndPagination = studentService.findStudentsWithSortingAndPagination(offset, pageSize, field);
        return new APIResponse<>(studentsWithSortingAndPagination.getSize(), studentsWithSortingAndPagination);
    }

    @GetMapping("/studentLecturers/{studentId}")
    public ResponseEntity<List<Lecturer>> getStudentLecturers(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok(studentService.getStudentLecturers(studentId));
    }

    @GetMapping("/filterStudents/{keyword}")
    public ResponseEntity<List<Student>> filterStudents(@PathVariable String keyword) {
        return ResponseEntity.ok(studentService.filterStudents(keyword));
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @DeleteMapping(path = "/{studentId}/removeLecturer/{lecturerId}")
    public void removeLecturerFromStudent(@PathVariable("studentId") Long studentId, @PathVariable("lecturerId") Long lecturerId) {
        studentService.removeLecturerToStudent(studentId, lecturerId);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}


