package com.msku.drugdosemonitoringsystem.services.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ImplEmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailToUser(String email, String token) {
        String from = "ilacdozutakipsistemi.app@gmail.com";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(from);
        mailMessage.setSubject("Doğrulama Kodu - Ilac Dozu Takip Sistemi");
        mailMessage.setText("Doğrulama Kodunuz: "+token.substring(0,4));
        mailSender.send(mailMessage);
    }

}

