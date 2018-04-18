package com.highthon.highthon3server.dto.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationConditionDto {
    // TODO: 패턴 매칭 Validation 추가. Controller에도 @Valid 추가할 것
    private String email;
    private String password;
}
