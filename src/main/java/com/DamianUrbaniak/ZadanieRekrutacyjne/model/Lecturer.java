package com.DamianUrbaniak.ZadanieRekrutacyjne.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "lecturers")
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
