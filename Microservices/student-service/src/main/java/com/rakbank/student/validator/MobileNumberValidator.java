package com.rakbank.student.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<ValidateMobileNumber, String> {

    private static final String MOBILE_NUMBER_PATTERN = "^\\+971(50|55|56|58|52)\\d{7}$";

    @Override
    public void initialize(ValidateMobileNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        if (mobileNumber == null) {
            return false;
        }
        return mobileNumber.matches(MOBILE_NUMBER_PATTERN);
    }
}
