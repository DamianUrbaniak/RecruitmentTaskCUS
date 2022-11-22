package com.DamianUrbaniak.ZadanieRekrutacyjne.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AdultValidate.class)
public @interface IsAdult {

    public String message() default "Student is not an adult.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
