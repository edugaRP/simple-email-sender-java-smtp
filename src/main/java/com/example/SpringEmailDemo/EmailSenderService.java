package com.example.SpringEmailDemo;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;


@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(List<String> toEmails,
                                String subject,
                                String body) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("edulindorodrigues@gmail.com");
        helper.setTo(toEmails.toArray(new String[0]));
        helper.setSubject(subject);
        helper.setText(body);
        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }

    public void sendEmailWithAttachment(List<String> toEmails,
                                        String subject,
                                        String body,
                                        String attachmentPath) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("example@gmail.com");
        helper.setTo(toEmails.toArray(new String[0]));
        helper.setSubject(subject);
        helper.setText(body);
        FileSystemResource file = new FileSystemResource(new File(attachmentPath));
        helper.addAttachment(file.getFilename(), file);
        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }

    public void sendHtmlEmail(List<String> toEmails,
                              String subject,
                              String body) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("edulindorodrigues@gmail.com");
        helper.setTo(toEmails.toArray(new String[0]));
        helper.setSubject(subject);
        helper.setText("<html><body>" + body + "</body></html>", true);
        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }
}
