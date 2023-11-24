package com.joel.newsapp.services;

import com.joel.newsapp.dtos.mail.SendMailDTO;
import com.joel.newsapp.services.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;


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
            emailSender.send(mail);
            return "Mail enviado correctamente a: " + mailDTO.getTo();
        } catch (MailException ex) {
            return "Error al enviar mail: " + ex.getMessage();
        }

    }
}
