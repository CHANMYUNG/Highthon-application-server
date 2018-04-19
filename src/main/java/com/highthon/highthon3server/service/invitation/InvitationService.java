package com.highthon.highthon3server.service.invitation;

import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.invitation.Invitation;
import com.highthon.highthon3server.domain.invitation.InvitationRepository;
import com.highthon.highthon3server.dto.invitation.InvitationDto;
import com.highthon.highthon3server.exception.DuplicatedValueException;
import com.highthon.highthon3server.support.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.sql.SQLException;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private Mailer mailer;

    // 메일 발신 실패시 Rollback
    @Transactional(rollbackFor = {MessagingException.class})
    public void inviteNewAdmin(InvitationDto invitationDto) throws MessagingException {
        final String email = invitationDto.getEmail();

        if (adminRepository.countByEmail(email) > 0)
            throw new DuplicatedValueException("email");

        if (invitationRepository.existsById(email))
            invitationRepository.deleteById(email);

        Invitation invitation = invitationDto.toEntity();

        invitationRepository.save(invitation);

        final String invitationCode = invitation.getInvitationCode();
        mailer.sendInvitationMail(email, invitationCode);
    }
}
