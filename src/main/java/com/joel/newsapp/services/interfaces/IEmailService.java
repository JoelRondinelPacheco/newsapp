package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.mail.SendMailDTO;

public interface IEmailService {
    String sendMail(SendMailDTO mail);
}
