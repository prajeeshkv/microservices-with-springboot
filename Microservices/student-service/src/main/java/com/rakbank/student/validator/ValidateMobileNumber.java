package com.rakbank.student.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = MobileNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateMobileNumber {


    String message() default "Mobile Number format should be seven number followed by +971(50|55|56|58|52) ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
