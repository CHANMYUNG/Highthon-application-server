package com.highthon.highthon3server.dto.application;

import com.highthon.highthon3server.domain.application.Application;
import com.highthon.highthon3server.domain.application.Area;
import com.highthon.highthon3server.domain.application.Position;
import com.highthon.highthon3server.domain.application.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationSaveDto {
    private String name;
    private Sex sex;
    private String phone;
    private String email;
    private String password;
    private Area area;
    private String belong;
    private Position position;

    @Builder
    public ApplicationSaveDto(String name, Sex sex, String phone, String email, String password, Area area, String belong, Position position) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.area = area;
        this.belong = belong;
        this.position = position;
    }

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
