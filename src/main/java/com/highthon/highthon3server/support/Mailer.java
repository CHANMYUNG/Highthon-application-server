package com.highthon.highthon3server.support;

import com.highthon.highthon3server.validator.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class Mailer {

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    private Properties props;

    private Session session;

    private MimeMessage message;

    public Mailer() {
        initializeProps();
        // initializeSession(); < 수행하면 @Value Injection이 안된 상태라 NPE 뜸
    }

    private void initializeMessage() throws MessagingException {
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
    }

    private void initializeSession() {
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private void initializeProps() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.EnableSSL.enable", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
    }

    public void sendInvitationMail(@Email String to, String invitationCode) throws MessagingException {

        initializeSession();
        initializeMessage();
        // Compose the message

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Subject
        message.setSubject("[제 3회 하이톤] 운영진 초대");

        // Text
        message.setContent("<h1>Highthon Invitation Link</h1><br><a href=http://www.highthon.kr/invitation?invitationCode=" + invitationCode + ">여기를 누르세요</a>", "text/html;charset=utf8");

        // send the message
        Transport.send(message);

    }
}
