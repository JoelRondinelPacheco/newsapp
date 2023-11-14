package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reporter")
public class ReporterController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/panel")
    public String newsPanel(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        //TODO Refactorizar que busque las noticias solo por email, porque si entro a este controller
        // el usuario ya existe (esta logeado)
        // TODO hacer que el correo de logeo sea unico
        Optional<User> userOptional = this.userRepository.findUser(username);
/*
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<News> userNews = this.newsService.getNewsByUser(user.getId());
            model.addAttribute("noticias", userNews);

        }*/
        return "news_panel.html";
    }

    @GetMapping("/form")
    public String formNews() {
        return "form_news.html";
    }



}
