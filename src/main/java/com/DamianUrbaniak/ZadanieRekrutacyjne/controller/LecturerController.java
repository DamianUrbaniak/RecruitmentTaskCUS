package com.DamianUrbaniak.ZadanieRekrutacyjne.controller;

import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.service.LecturerService;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.LecturerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @PostMapping("/createLecturer")
    public ResponseEntity<Lecturer> addLecturer(@RequestBody @Valid LecturerDTO lecturerDTO) {
        return new ResponseEntity<>(lecturerService.saveLecturer(lecturerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{lecturerId}/assignStudent/{studentId}")
    public void assignStudentToLecturer(@PathVariable("lecturerId") Long studentId, @PathVariable("studentId") Long lecturerId) {
        lecturerService.assignStudentToLecturer(lecturerId, studentId);
    }

    @GetMapping("/getAllLecturers/{keyword}")
    public ResponseEntity<List<Lecturer>> getAllLecturers(@PathVariable String keyword) {
        return ResponseEntity.ok(lecturerService.getAllLecturers(keyword));
    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<Lecturer> getLecturer(@PathVariable Long lecturerId) {
        return ResponseEntity.ok(lecturerService.getLecturer(lecturerId));
    }

    @DeleteMapping(path = "{lecturerId}")
    public void deleteLecturer(@PathVariable("lecturerId") Long lecturerId) {
        lecturerService.deleteLecturer(lecturerId);
    }
}
