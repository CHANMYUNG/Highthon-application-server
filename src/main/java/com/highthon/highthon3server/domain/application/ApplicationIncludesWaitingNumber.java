package com.highthon.highthon3server.domain.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.highthon.highthon3server.domain.application.Area;
import com.highthon.highthon3server.domain.application.Position;
import com.highthon.highthon3server.domain.application.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationIncludesWaitingNumber {
    private Long applicationId;
    private String name;
    private String email;
    private Sex sex;
    private Area area;
    private Position position;
    private String phone;
    private String belong;
    private Boolean isAccepted;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdDate;
    private Long waitingNumber;

    public ApplicationIncludesWaitingNumber(Long applicationId, String name, String email, Sex sex, Area area, Position position, String phone, String belong, Boolean isAccepted, LocalDateTime createdDate, Long waitingNumber) {
        this.applicationId = applicationId;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.area = area;
        this.position = position;
        this.phone = phone;
        this.belong = belong;
        this.isAccepted = isAccepted;
        this.createdDate = createdDate;
        this.waitingNumber = waitingNumber;
    }
}
