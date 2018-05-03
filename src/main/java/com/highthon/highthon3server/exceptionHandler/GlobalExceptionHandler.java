package com.highthon.highthon3server.exceptionHandler;


import com.highthon.highthon3server.exception.ApplicationNotFoundException;
import com.highthon.highthon3server.exception.AuthenticationException;
import com.highthon.highthon3server.exception.DuplicatedValueException;
import com.highthon.highthon3server.exception.SelfHandleException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    protected ErrorResponse duplicatedValueException(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }

    @ExceptionHandler(value = {ApplicationNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ErrorResponse applicationNotFoundException(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }

    @ExceptionHandler(value = {SelfHandleException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse selfHandleException(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }

    @ExceptionHandler(value = {AuthenticationException.class, UsernameNotFoundException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorResponse authenticationExceptionHandler(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }
}
