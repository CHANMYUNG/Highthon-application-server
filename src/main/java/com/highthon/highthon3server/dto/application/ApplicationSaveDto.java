package com.highthon.highthon3server.dto.application;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.Area;
import com.highthon.highthon3server.domain.application.Position;
import com.highthon.highthon3server.domain.application.Sex;
import com.highthon.highthon3server.validator.Belong;
import com.highthon.highthon3server.validator.Email;
import com.highthon.highthon3server.validator.Password;
import com.highthon.highthon3server.validator.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationSaveDto {
    // TODO: 패턴 매칭 Validation 추가. Controller에도 @Valid 추가할 것

    @NotNull
    private String name;

    @NotNull
    private Sex sex;

    @NotNull
    @Phone
    private String phone;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Password
    private String password;

    @NotNull
    private Area area;

    @NotNull
    @Belong
    private String belong;

    @NotNull
    private Position position;

    public Application toEntity() {
        return Application.builder()
                .name(name)
                .sex(sex)
                .phone(phone)
                .email(email)
                .password(password)
                .area(area)
                .position(position)
                .belong(belong)
                .isAccepted(false)
                .build();
    }

}
