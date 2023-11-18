package com.joel.newsapp.services;

import com.joel.newsapp.dtos.mail.SendMailDTO;
import com.joel.newsapp.services.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService implements IMailService {
    @Autowired
    private JavaMailSender mailSender;


    @Value("$(spring.email.username)")
    private String fromMail;
    @Override
    public String sendMail(SendMailDTO mailDTO) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(fromMail);
            mail.setTo(mailDTO.getTo());
            mail.setSubject(mailDTO.getSubject());
            mail.setText(mailDTO.getMessage());
            mailSender.send(mail);
            return "Mail enviado correctamente a: " + mailDTO.getTo();
        } catch (MailException ex) {
            return "Error al enviar mail: " + ex.getMessage();
        }

    }
}
