package com.highthon.highthon3server.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "비밀번호는 영문 대/소문자와 특수문자를 혼합한 8~15자여야 합니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
