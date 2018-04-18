package com.highthon.highthon3server.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Pattern.matches("(\\d{3}).*(\\d{3}).*(\\d{4})", value.toString());
    }
}
