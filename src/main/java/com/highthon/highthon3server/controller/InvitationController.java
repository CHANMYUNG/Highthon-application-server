package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.dto.invitation.InvitationDto;
import com.highthon.highthon3server.service.invitation.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping("/invitation")
    @ResponseStatus(HttpStatus.OK)
    public void inviteNewAdmin(@Valid @RequestBody InvitationDto invitationDto) throws MessagingException {
        invitationService.inviteNewAdmin(invitationDto);
    }
}
