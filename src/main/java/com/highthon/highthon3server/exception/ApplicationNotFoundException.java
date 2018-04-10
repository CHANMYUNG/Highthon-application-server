package com.highthon.highthon3server.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException() {
        super("신청 정보를 찾을 수 없습니다.");
    }
}
