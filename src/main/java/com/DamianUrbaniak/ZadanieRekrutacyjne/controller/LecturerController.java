package com.DamianUrbaniak.ZadanieRekrutacyjne.controller;

import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.APIResponse;
import com.DamianUrbaniak.ZadanieRekrutacyjne.model.Lecturer;
import com.DamianUrbaniak.ZadanieRekrutacyjne.service.LecturerService;
import com.DamianUrbaniak.ZadanieRekrutacyjne.dto.LecturerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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

    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Lecturer>> getLecturersWithSortingAndPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Lecturer> lecturersWithSortingAndPagination = lecturerService.findLecturersWithSortingAndPagination(offset, pageSize, field);
        return new APIResponse<>(lecturersWithSortingAndPagination.getSize(), lecturersWithSortingAndPagination);
    }

    @GetMapping("/getAllLecturers")
    public ResponseEntity<Set<Lecturer>> getAllLecturers() {
        return ResponseEntity.ok(lecturerService.getAllLecturers());
    }

    @GetMapping("/filterLecturers/{keyword}")
    public ResponseEntity<Set<Lecturer>> filterLecturers(@PathVariable String keyword) {
        return ResponseEntity.ok(lecturerService.filterLecturers(keyword));
    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<Lecturer> getLecturer(@PathVariable Long lecturerId) {
        return ResponseEntity.ok(lecturerService.getLecturer(lecturerId));
    }

    @DeleteMapping(path = "/{lecturerId}/removeStudent/{studentId}")
    public void removeLecturerFromStudent(@PathVariable("lecturerId") Long lecturerId, @PathVariable("studentId") Long studentId) {
        lecturerService.removeStudentfromLecturer(lecturerId, studentId);
    }

    @DeleteMapping(path = "{lecturerId}")
    public void deleteLecturer(@PathVariable("lecturerId") Long lecturerId) {
        lecturerService.deleteLecturer(lecturerId);
    }
}
