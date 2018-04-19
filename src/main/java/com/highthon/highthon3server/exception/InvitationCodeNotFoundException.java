package com.highthon.highthon3server.exception;

public class InvitationCodeNotFoundException extends RuntimeException {
    public InvitationCodeNotFoundException() {
        super("invitation code is not available (not found)");
    }
}
