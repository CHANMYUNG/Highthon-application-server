package com.highthon.highthon3server.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    @JsonFormat(pattern = "yyy-MM-dd kk:mm:ss.SSS")
    private LocalDateTime timestamp;
    private String message;
    private String path;

    public ErrorResponse(String path, Exception e) {
        this.path = path;
        this.message = e.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}
