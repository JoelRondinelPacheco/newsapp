package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsEditReqDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsRepository;
import com.joel.newsapp.services.CommentService;
import com.joel.newsapp.services.NewsService;
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
    private NewsService newsService;
    @Autowired
    private INewsRepository newsRepository;
    @Autowired
    private CommentService commentService;


    @GetMapping("/{id}")
    public String getNewById(@PathVariable Long id, ModelMap model) {
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
    public String editarNoticia(@PathVariable Long id, ModelMap model){
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
    public String editarNoticiaDB(@PathVariable Long id, @RequestParam String title, @RequestParam String body, @RequestParam List<String> categories, MultipartFile image, ModelMap model){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String author = auth.getName();
            Boolean isAdmin = auth.getAuthorities().contains("ROLE_"+Role.ADMIN);
            NewsEditReqDTO newsDTO = new NewsEditReqDTO(title, body, categories, author, image, id, isAdmin);

            this.newsService.edit(newsDTO);

            model.put("message", "Noticia editada con exito");
        } catch (NotFoundException e) {
            model.put("message", "La noticia no existe");
        } finally {
            return "newspanel.html";
        }
    }

}
