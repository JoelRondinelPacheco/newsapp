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
            System.out.println("entro a envuar mail");
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(fromMail);
            mail.setTo(mailDTO.getTo());
            mail.setSubject(mailDTO.getSubject());
            mail.setText(mailDTO.getMessage());
            System.out.println("Antes de enviarlo");
            emailSender.send(mail);
            System.out.println("Despues de enviarlo");
            return "Mail enviado correctamente a: " + mailDTO.getTo();
        } catch (MailException ex) {
            return "Error al enviar mail: " + ex.getMessage();
        }

    }
}
