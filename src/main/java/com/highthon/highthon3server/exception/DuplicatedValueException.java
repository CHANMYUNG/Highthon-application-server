package com.highthon.highthon3server.exception;

public class DuplicatedValueException extends RuntimeException {
    public DuplicatedValueException(String column) {
        super(column);
    }
}
