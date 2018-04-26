package com.highthon.highthon3server.service.mail;

import com.highthon.highthon3server.domain.application.ApplicationRepository;
import com.highthon.highthon3server.dto.mail.SendMailDto;
import com.highthon.highthon3server.dto.mail.To;
import com.highthon.highthon3server.support.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private Mailer mailer;

    @Autowired
    private ApplicationRepository applicationRepository;

    public void sendEmail(SendMailDto dto) throws FileNotFoundException, MessagingException {
        final String title = dto.getTitle();
        final String content = dto.getContent();
        final To to = dto.getTo();
        final List<String> filenames = dto.getFilenames();

        for (String filename : filenames) {
            if (!(new File("uploads/" + filename).canRead()))
                throw new FileNotFoundException(filename + "을 찾을 수 없습니다.");
        }

        List<String> emailList = getEmailAddresses(to);

        mailer.sendEmail(title, emailList, content, filenames);
    }


    private List<String> getEmailAddresses(To to) {
        if (to == To.ACCEPTED) {
            return applicationRepository.getEmailListByIsAcceptedIsTrue();
        } else if (to == To.WAITING) {
            return applicationRepository.getEmailListByIsAcceptedIsFalse();
        } else return applicationRepository.getAllEmailList();
    }
}
