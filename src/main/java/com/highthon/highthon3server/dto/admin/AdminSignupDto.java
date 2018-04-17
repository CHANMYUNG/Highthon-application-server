package com.highthon.highthon3server.dto.admin;


import com.highthon.highthon3server.domain.admin.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AdminSignupDto {
    //TODO: define dto class

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String belong;

    @NotNull
    private String password;

    @NotNull
    private String phone;

    public Admin toEntity() {
        return Admin.builder()
                .name(name)
                .email(email)
                .belong(belong)
                .password(password)
                .phone(phone)
                .build();
    }
}
