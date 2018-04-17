package com.highthon.highthon3server.exception;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException() {
        super("Admin not found");
    }

    public AdminNotFoundException(String adminId) {
        super("Admin " + adminId + " not found");
    }
}
