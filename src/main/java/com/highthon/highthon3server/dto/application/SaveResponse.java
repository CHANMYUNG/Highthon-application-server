package com.highthon.highthon3server.dto.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaveResponse {
    boolean isAccepted;
    Integer waitingNumber;
}
