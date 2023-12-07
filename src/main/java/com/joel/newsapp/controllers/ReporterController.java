package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsForm;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.dtos.newscategory.CategoriesFormDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.NewsCategoryService;
import com.joel.newsapp.services.NewsService;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.BuildDTOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reporter")
public class ReporterController {

    @Autowired private NewsService newsService;
    @Autowired private NewsCategoryService categoryService;
    @Autowired private IUserService userService;
    @Autowired private BuildDTOs dto;

    @GetMapping("/panel")
    public String newsPanel(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<News> userNews = this.newsService.getNewsByUser(username);
        model.addAttribute("userNews", userNews);
        return "reporter/news_panel.html";
    }

    @GetMapping("/form")
    public String formNews(ModelMap model) {

        List<NewsCategory> categories = this.categoryService.findAll();
        List<CategoriesFormDTO> categoriesDTO = new ArrayList<>();

        for (NewsCategory c : categories) {
            categoriesDTO.add(new CategoriesFormDTO(c.getName(), c.getId(), false));
        }

        NewsForm form = this.dto.newsForm(null, categoriesDTO);
        model.addAttribute("form", form);
        return "form_news";
    }

}
