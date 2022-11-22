package com.DamianUrbaniak.ZadanieRekrutacyjne.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AdultValidate implements ConstraintValidator<IsAdult, LocalDate> {
    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        Integer age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if (age < 18) {
            return false;
        }
        return true;
    }
}
