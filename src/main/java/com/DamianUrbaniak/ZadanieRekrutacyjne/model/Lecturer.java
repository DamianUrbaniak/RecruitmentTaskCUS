package com.DamianUrbaniak.ZadanieRekrutacyjne.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String lastName;

    @Transient
    private Integer age;

    private LocalDate dateOfBirth;

    private String email;

    private String subject;

    @ManyToMany
    private final List<Student> students = new ArrayList<>();

    public Lecturer(String name,
                    String lastName,
                    LocalDate dateOfBirth,
                    String email,
                    String subject) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.subject = subject;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
