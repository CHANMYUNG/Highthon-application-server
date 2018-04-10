package com.highthon.highthon3server.domain.application;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationCondition {
    private String name;
    private Boolean isAccepted;
    private Long waitingNumber;

    public ApplicationCondition(String name, Boolean isAccepted, Long waitingNumber) {
        this.name = name;
        this.isAccepted = isAccepted;
        this.waitingNumber = waitingNumber;
    }
}
