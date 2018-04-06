package com.highthon.highthon3server.dto.application;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.ApplyType;
import com.highthon.highthon3server.domain.application.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationSaveDto {
    private String name;
    private Sex sex;
    private String phone;
    private String email;
    private String password;
    private ApplyType applyType;

    @Builder
    public ApplicationSaveDto(String name, Sex sex, String phone, String email, String password, ApplyType applyType) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.applyType = applyType;
    }

    public Application toEntity() {
        return Application.builder()
                .name(name)
                .sex(sex)
                .phone(phone)
                .email(email)
                .password(password)
                .applyType(applyType)
                .build();
    }

}
