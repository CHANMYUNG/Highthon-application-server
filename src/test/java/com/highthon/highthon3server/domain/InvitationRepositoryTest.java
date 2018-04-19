package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.invitation.Invitation;
import com.highthon.highthon3server.domain.invitation.InvitationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvitationRepositoryTest {
    @Autowired
    private InvitationRepository invitationRepository;

    private String INVITATION_EMAIL = "test0@test.com";

    private String INVITATION_CODE;

    @Before
    public void setup() {
        invitationRepository.deleteAll();
        Invitation invitation = new Invitation(INVITATION_EMAIL);
        INVITATION_CODE = invitation.getInvitationCode();
        invitationRepository.save(invitation);
    }

    @Test
    public void SAVE_FIND_테스트() {
        Invitation invitation = invitationRepository.findByInvitationCode(INVITATION_CODE).orElse(null);

        assertThat(invitation.getEmail(), is(INVITATION_EMAIL));
        assertThat(invitation.getInvitationCode(), is(INVITATION_CODE));
    }
}
