package com.highthon.highthon3server.dto.auth;


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
    // TODO: Super 관리자가 새로운 관리자를 초대하는 형식이므로, 초대 코드 필드가 추가되어야 함
    // TODO: 비밀번호, 휴대전화번호 패턴 Validation 추가

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
