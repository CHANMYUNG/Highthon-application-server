package com.highthon.highthon3server.dto.mail;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class SendMailDto {

    @NotNull
    String title;

    @NotNull
    To to;

    @NotNull
    String content;

    @NotNull
    List<String> filenames;
}
