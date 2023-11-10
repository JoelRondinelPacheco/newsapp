package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.News;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private INewsCategoryService categoryService;
    @GetMapping("/")
    public String index(ModelMap model) {

        try {
            // DESTACADA crear categoria destacada
            News featured = this.newsService.featured();
            // LISTA DE CATEGORIAS
            List<String> categories = this.categoryService.getAllCategories();
            List<List<News>> news = new ArrayList<>();
            // 10? DE CADA CATEGORIA
            for(String category : categories) {
                //TODO POR FECHA?
                news.add(this.newsService.findByCategory(category, 10));
            }
            // 5 ULTIMAS NOTICIAS

        } catch (NotFoundException ex) {

        }

        model.addAttribute("news", news);
        return "index.html"; }
}
