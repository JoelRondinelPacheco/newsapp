package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.NewsCategoryService;
import com.joel.newsapp.services.NewsService;
import com.joel.newsapp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reporter")
public class ReporterController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsCategoryService categoryService;
    @Autowired
    private IUserService userService;

    @GetMapping("/panel")
    public String newsPanel(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println(username);
        List<News> userNews = this.newsService.getNewsByUser(username);
        System.out.println(userNews.size());
        for (News n : userNews) {
            System.out.println(n.getTitle());
            System.out.println(n.getAuthor().getUser().getEmail());
        }
        model.addAttribute("userNews", userNews);
        return "reporter/news_panel.html";
    }

    @GetMapping("/form")
    public String formNews(ModelMap model) {
        List<NewsCategory> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);

        return "form_news.html";
    }



}
