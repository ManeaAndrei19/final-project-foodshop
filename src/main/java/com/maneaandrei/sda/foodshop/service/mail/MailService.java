package com.maneaandrei.sda.foodshop.service.mail;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import com.maneaandrei.sda.foodshop.config.MailProperties;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final MailProperties mailProperties;

    public MailService(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    public void sendMail(String from, String to, String subject, String content) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", mailProperties.getAuth());
        properties.put("mail.smtp.starttls.enable", mailProperties.getStarttls());
        properties.put("mail.smtp.host", mailProperties.getHost());
        properties.put("mail.smtp.port", mailProperties.getPort());
        properties.put("mail.smtp.ssl.trust", mailProperties.getTrust());

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }


}