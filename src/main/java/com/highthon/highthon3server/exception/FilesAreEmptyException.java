package com.highthon.highthon3server.exception;

public class FilesAreEmptyException extends RuntimeException {
    public FilesAreEmptyException() {
        super("파일을 선택해 업로드해주세요.");
    }
}
