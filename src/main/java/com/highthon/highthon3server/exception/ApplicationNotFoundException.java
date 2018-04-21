package com.highthon.highthon3server.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException() {
        super("신청 정보를 찾을 수 없습니다.");
    }

    public ApplicationNotFoundException(Long applicationId) {
        super("applicationId '" + applicationId + "' 에 해당하는 신청 정보를 찾을 수 없습니다.");
    }
}
