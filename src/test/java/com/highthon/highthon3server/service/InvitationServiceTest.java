package com.highthon.highthon3server.service;

import com.highthon.highthon3server.dto.invitation.InvitationDto;
import com.highthon.highthon3server.service.invitation.InvitationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvitationServiceTest {

    @Autowired
    private InvitationService invitationService;

    @Test
    public void 이메일을_보낸다() {

        InvitationDto dto = new InvitationDto("nooheat_@naver.com");

        Exception anyException = null;
        try {
            invitationService.inviteNewAdmin(dto);
        } catch (MessagingException e) {
            anyException = e;
        }

        assertThat(anyException, is(nullValue()));
    }

}
