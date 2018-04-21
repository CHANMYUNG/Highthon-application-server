package com.highthon.highthon3server.exception;

public class SelfHandleException extends RuntimeException {
    public SelfHandleException() {
        super("Can not target yourself");
    }
}
