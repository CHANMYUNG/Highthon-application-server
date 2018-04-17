package com.highthon.highthon3server.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AdminLoginDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
