package com.api.moedaestudantil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class EmailService {
    @Autowired
    private MailSender mailSender;

    @Autowired
    private MailMessage mailMessage;


    public void sendEmail(String to, String subject, String body){
        try {
            mailMessage.setFrom("moedaestudantilpuc@outlook.com.br");
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(body);

            mailSender.send((SimpleMailMessage) mailMessage);
            System.out.println("E-mail enviado com sucesso!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
