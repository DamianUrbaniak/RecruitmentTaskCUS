package com.DamianUrbaniak.ZadanieRekrutacyjne.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Student_Lecturer",
            joinColumns = { @JoinColumn(name = "student") },
            inverseJoinColumns = { @JoinColumn(name = "lecturer_id") })
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

    public void assignLecturer(Lecturer lecturer) {
        this.lecturers.add(lecturer);
    }
}
