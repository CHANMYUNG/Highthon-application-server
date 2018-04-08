package com.highthon.highthon3server.exception;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException(String column) {
        super(column);
    }
}
