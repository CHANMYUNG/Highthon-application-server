package com.highthon.highthon3server.dto.application;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.Area;
import com.highthon.highthon3server.domain.application.Position;
import com.highthon.highthon3server.domain.application.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationSaveDto {
    // TODO: 패턴 매칭 Validation 추가. Controller에도 @Valid 추가할 것
    private String name;
    private Sex sex;
    private String phone;
    private String email;
    private String password;
    private Area area;
    private String belong;
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
                .build();
    }

}
