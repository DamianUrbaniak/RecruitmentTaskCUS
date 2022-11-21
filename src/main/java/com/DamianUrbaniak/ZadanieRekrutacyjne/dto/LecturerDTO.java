package com.DamianUrbaniak.ZadanieRekrutacyjne.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.time.LocalDate;

@Data
@NoArgsConstructor(staticName = "build")
@AllArgsConstructor
public class LecturerDTO {

    private String name;

    private String lastName;

    @Transient
    private Integer age;

    private LocalDate dateOfBirth;

    private String email;

    private String subject;

}
