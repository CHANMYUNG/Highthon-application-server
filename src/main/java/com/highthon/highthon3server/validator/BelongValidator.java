package com.highthon.highthon3server.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class BelongValidator implements ConstraintValidator<Belong, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Pattern.matches("^[a-zA-Z가-힣0-9]+등학교$", value.toString().replace(" ", ""));
    }
}
