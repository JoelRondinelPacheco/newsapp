package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsEditReqDTO;
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
@RequestMapping("/")
public class NewsController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private INewsRepository newsRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private INewsCategoryService categoryService;

    @GetMapping("/{category}")
    public String getByCategory(@PathVariable String category) {
        try {
            NewsCategory categoryEntiy = this.categoryService.findByName(category);
            List<News> news = this.newsService
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        return "news_category";
    }

    @GetMapping("/{id}")
    public String getNewById(@PathVariable String id, ModelMap model) {
        try {
            News news = this.newsService.getById(id);
            List<Comment> comments = this.commentService.getAllNewsComments(id);
            model.addAttribute("comments", comments);
            model.addAttribute("news", news);
            return "/noticia.html";
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/edit/{id}")
    public String editarNoticia(@PathVariable String id, ModelMap model){
        try {
            News noticia = this.newsService.getById(id);
            model.addAttribute("noticia", noticia);
            return "editnew.html";
        } catch (NotFoundException e) {
            model.put("error", e.getMessage());
            return "editnew.html";
        }
    }

    @PostMapping("/edit/{id}")
    public String editarNoticiaDB(@PathVariable String id, @RequestParam String title, @RequestParam String subtitle, @RequestParam String imageCaption, @RequestParam String body, @RequestParam List<String> categories, MultipartFile image, ModelMap model){
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
            return "newspanel.html";
        }
    }

}
