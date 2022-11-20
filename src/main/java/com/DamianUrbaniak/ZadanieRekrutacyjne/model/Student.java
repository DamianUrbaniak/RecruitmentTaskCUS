package com.DamianUrbaniak.ZadanieRekrutacyjne.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String lastName;

    @Transient
    private Integer age;

    private LocalDate dateOfBirth;

    private String email;

    private String field;

    @ManyToMany
    private final List<Lecturer> lecturers = new ArrayList<>();

    public Student(String name,
                   String lastName,
                   LocalDate dateOfBirth,
                   String email,
                   String field) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.field = field;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
