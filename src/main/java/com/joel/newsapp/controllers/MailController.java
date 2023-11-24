package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.mail.SendMailDTO;
import com.joel.newsapp.services.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private IEmailService mailService;

    @PostMapping("/send/{to}")
    public String sendMail(@PathVariable String to, @RequestParam String subject, @RequestParam String message, ModelMap model) {
        SendMailDTO mail = SendMailDTO.builder()
                .to(to)
                .subject(subject)
                .message(message)
                .build();
        String res = this.mailService.sendMail(mail);
        model.put("emailResponse", res);
        return "index";

    }
}
