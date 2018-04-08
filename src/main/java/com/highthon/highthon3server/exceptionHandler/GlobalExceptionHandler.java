package com.highthon.highthon3server.exceptionHandler;


import com.highthon.highthon3server.exception.DuplicatedValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DuplicatedValueException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    protected ErrorResponse duplicatedValueException(HttpServletRequest request, Exception exception) {
        return new ErrorResponse(request.getRequestURL().toString(), exception.getMessage());
    }

}
