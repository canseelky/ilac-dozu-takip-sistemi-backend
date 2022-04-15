package com.msku.drugdosemonitoringsystem.services.emailservice;

public interface IEmailService {
    void sendEmailToUser(String email, String token);

}
