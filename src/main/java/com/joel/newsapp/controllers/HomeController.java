package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.ICommentService;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private ICommentService commentService;
    @GetMapping("/")
    public String index() {
/*
        try {
            // DESTACADA crear categoria destacada
            News mainFeatured = this.newsService.mainFeatured();
            // LISTA DE CATEGORIAS
            List<String> categories = this.categoryService.getAllCategories();
            List<List<News>> news = new ArrayList<>();
            // 10? DE CADA CATEGORIA
            for (String category : categories) {
                //TODO POR FECHA?
                news.add(this.newsService.findByCategory(category, 10));
            }
            // 5 ULTIMAS NOTICIAS
            List<News> latest = this.newsService.latest(5);
            model.addAttribute("featured", mainFeatured);
            model.addAttribute("categories", categories);
            model.addAttribute("news", news);
            model.addAttribute("latest", latest);
            return "index";
        } catch (NotFoundException ex) {
            model.put("error", ex.getMessage());
            return "index.html";
        }*/
        System.out.println("entro");
        return "index";
    }

    //TODO Main controller for repeated values in news

    @GetMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, ModelMap model) throws NotFoundException {
        try {
            // Main featured
            NewsCategory categoryEntiy = this.categoryService.findByName(category);
            // Main featured by category
            News mainFeatured = this.newsService.featuredByCategory(category);
            // Featured by category
            News featuredNews = this.newsService.featuredByCategory(category);
            // Latest by category
            List<News> latest = this.newsService.latestByCategory(category, 5);
            model.addAttribute("category", categoryEntiy);
            model.addAttribute("main", mainFeatured);
            model.addAttribute("featured", featuredNews);
            model.addAttribute("latest", latest);
            return "news_category";

        } catch (NotFoundException e) {
            System.out.println("ingreso metodo por categoruia");
            throw new NotFoundException("Excepcion del metodo /cat");
        }
    }

    @GetMapping("/category/{category}/{id}")
    public String getNewById(@PathVariable String category, @PathVariable String id, ModelMap model) throws Exception {
        try {
            News news = this.newsService.getById(id);
            List<Comment> comments = this.commentService.getAllNewsComments(id);
            model.addAttribute("comments", comments);
            model.addAttribute("news", news);
            return "noticia";
        } catch (NotFoundException e) {
            System.out.println("Ingreso al metodo");
            throw new Exception("Excepcion del metodo /cat/id");
        }
    }


}
