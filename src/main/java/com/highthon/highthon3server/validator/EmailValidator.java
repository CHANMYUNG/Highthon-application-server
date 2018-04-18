package com.highthon.highthon3server.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", value.toString());
    }
}
