package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.mail.SendMailDTO;

public interface IMailService {
    String sendMail(SendMailDTO mail);
}
