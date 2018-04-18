package com.highthon.highthon3server.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "이메일 형식이 아닙니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
