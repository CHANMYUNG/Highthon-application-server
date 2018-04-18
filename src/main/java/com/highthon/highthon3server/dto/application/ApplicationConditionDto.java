package com.highthon.highthon3server.dto.application;

import com.highthon.highthon3server.validator.Email;
import com.highthon.highthon3server.validator.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationConditionDto {
    // TODO: 패턴 매칭 Validation 추가. Controller에도 @Valid 추가할 것

    @Email
    private String email;

    @Password
    private String password;
}
