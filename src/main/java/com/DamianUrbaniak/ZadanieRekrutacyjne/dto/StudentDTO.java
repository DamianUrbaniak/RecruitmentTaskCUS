package com.DamianUrbaniak.ZadanieRekrutacyjne.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class StudentDTO {


    private String name;

    private String lastName;

    @Transient
    private Integer age;

    private LocalDate dateOfBirth;

    @Email(message = "Invalid format of email!")
    private String email;

    @NotEmpty
    private String field;
}
