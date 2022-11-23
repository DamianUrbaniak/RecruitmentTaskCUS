package com.DamianUrbaniak.ZadanieRekrutacyjne.dto;

import com.DamianUrbaniak.ZadanieRekrutacyjne.validation.IsAdult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class StudentDTO {


    @NotBlank
    @Pattern(regexp = "^[\\s\\p{L}]{3,25}$", message = "Name must be at least 3 characters long.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[\\s\\p{L}]{3,25}$", message = "Last name must be at least 3 characters long.")
    private String lastName;

    private Integer age;

    @IsAdult
    private LocalDate dateOfBirth;

    @Email(message = "Invalid format of email!")
    private String email;

    @NotBlank
    private String field;
}
