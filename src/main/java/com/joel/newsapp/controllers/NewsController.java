package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsEditReqDTO;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsRepository;
import com.joel.newsapp.services.CommentService;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private INewsRepository newsRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private INewsCategoryService categoryService;

    @PostMapping("/create")
    public String addNew(@RequestParam String title, @RequestParam String subtitle, @RequestParam String imageCaption, @RequestParam String body, @RequestParam List<String> category, MultipartFile image, ModelMap model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            NewsPostReqDTO newsDTO = new NewsPostReqDTO(title, subtitle, imageCaption, body, category, username, image);
            News news = this.newsService.save(newsDTO);
            model.put("title", news.getTitle());
            model.put("body", news.getBody());
            return "form_news.html";
        } catch (NotFoundException ex) {
            model.put("error", "Error al cargar noticia");
            return "form_news.html";
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            return "form_news.html";
        }
    }

    @GetMapping("/edit/{id}")
    public String editNews(@PathVariable String id, ModelMap model){
        try {
            News news = this.newsService.getById(id);
            model.addAttribute("news", news);
            return "edit_new.html";
        } catch (NotFoundException e) {
            model.put("error", e.getMessage());
            return "edit_new.html";
        }
    }

    @PostMapping("/edit/{id}")
    public String postEditNews(@PathVariable String id, @RequestParam String title, @RequestParam String subtitle, @RequestParam String imageCaption, @RequestParam String body, @RequestParam List<String> categories, MultipartFile image, ModelMap model){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String author = auth.getName();
            Boolean isAdmin = auth.getAuthorities().contains("ROLE_"+Role.ADMIN);
            NewsEditReqDTO newsDTO = new NewsEditReqDTO(title, subtitle, imageCaption, body, categories, author, image, id, isAdmin);

            this.newsService.edit(newsDTO);

            model.put("message", "Noticia editada con exito");
        } catch (NotFoundException e) {
            model.put("message", "La noticia no existe");
        } finally {
            return "news_panel.html";
        }
    }

}
