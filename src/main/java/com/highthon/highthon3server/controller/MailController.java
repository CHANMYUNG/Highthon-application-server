package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.dto.mail.SendMailDto;
import com.highthon.highthon3server.exceptionHandler.ErrorResponse;
import com.highthon.highthon3server.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/mail")
    public void sendMail(@Valid @RequestBody SendMailDto sendMailDto) throws FileNotFoundException, MessagingException {
        mailService.sendEmail(sendMailDto);
    }

    @ExceptionHandler({MessagingException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse messagingExceptionHandler(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), new Exception("메일을 보내는 도중 오류가 발생했습니다. : " + e.getMessage()));
    }

    @ExceptionHandler({FileNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse fileNotFoundExceptionHandler(HttpServletRequest request, Exception e) {
        return new ErrorResponse(request.getRequestURI(), e);
    }
}
