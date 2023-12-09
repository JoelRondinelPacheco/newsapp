package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.comment.CommentViewDTO;
import com.joel.newsapp.dtos.news.NewsByCategoryDTO;
import com.joel.newsapp.dtos.news.NewsHomeDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.ICommentService;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import com.joel.newsapp.utils.BuildDTOs;
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
    @Autowired private INewsService newsService;
    @Autowired private INewsCategoryService categoryService;
    @Autowired private ICommentService commentService;
    @Autowired private BuildDTOs dtos;
    @GetMapping("/")
    public String index(ModelMap model) {

        try {
            NewsHomeDTO mainFeatured = this.newsService.mainFeatured();
            model.addAttribute("mainFeatured", mainFeatured);
        } catch (NotFoundException e) {
            System.out.println("main empty");
            model.addAttribute("mainFeaturedEmpty", true);
        }

        List<NewsCategory> categories = this.categoryService.findAll();
        if (categories.isEmpty()) {
            model.addAttribute("categoriesEmpty", true);
        }

        List<NewsByCategoryDTO> news = new ArrayList<>();
        for (NewsCategory category : categories) {
            List<News> newsCat = this.newsService.findByCategory(category.getId(), 4);
            System.out.println(newsCat.size());
            news.add(new NewsByCategoryDTO(category.getId(), category.getName(), this.dtos.newsHomeDTOList(newsCat), newsCat.isEmpty() ? true : false));

        }


        List<News> latestNews = this.newsService.latest(5);
        List<NewsHomeDTO> latest = this.dtos.newsHomeDTOList(latestNews);

        model.addAttribute("categories", categories);
        model.addAttribute("news", news);
        model.addAttribute("latest", latest);
        return "index";

    }

    @GetMapping("/category/{category}")
    public String getByCategory(@PathVariable String category, ModelMap model) throws NotFoundException {
        try {
            NewsCategory categoryEntity = this.categoryService.findByName(category);
            List<News> latest = this.newsService.latestByCategory(category, 5);
            model.addAttribute("category", categoryEntity);
            model.addAttribute("latest", latest);
            return "news_category";

        } catch (NotFoundException e) {
            System.out.println("ingreso metodo por categoruia");
            throw new NotFoundException("Excepcion del metodo /cat");
        }
    }

}
