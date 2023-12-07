package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.comment.CommentViewDTO;
import com.joel.newsapp.dtos.news.NewsEditReqDTO;
import com.joel.newsapp.dtos.news.NewsForm;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.dtos.news.NewsViewDTO;
import com.joel.newsapp.dtos.newscategory.CategoriesFormDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsRepository;
import com.joel.newsapp.services.CommentService;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import com.joel.newsapp.utils.BuildDTOs;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired private INewsService newsService;
    @Autowired private INewsRepository newsRepository;
    @Autowired private CommentService commentService;
    @Autowired private INewsCategoryService categoryService;
    @Autowired private BuildDTOs dto;

    @GetMapping("/{category}/{id}")
    public String getNews(@PathVariable String category, @PathVariable String id, ModelMap model) {
        try {
            NewsViewDTO news = this.newsService.getByIdDTO(id);
            model.addAttribute("news", news);
            List<CommentViewDTO> comments = this.commentService.getAllNewsComments(id);
            if (comments.isEmpty()) {
                model.addAttribute("commentsEmpty", "No comments");
            }
            model.addAttribute("comments", comments);
        } catch (NotFoundException e) {
            model.addAttribute("newsError", e.getMessage());
        }
        return "news_view";
    }

   @PostMapping("/save")
    public String addNew(@RequestParam String title,
                         @RequestParam String subtitle,
                         @RequestParam String imageCaption,
                         @RequestParam String body,
                         @RequestParam(required = false) List<String> categories,
                         @RequestParam String mainCategory,
                         MultipartFile image,
                         ModelMap model) {
        try {
            if (categories == null) {
                categories = new ArrayList<>();
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            System.out.println(body);
            NewsPostReqDTO newsDTO = new NewsPostReqDTO(title, subtitle, imageCaption, body, categories, mainCategory, username, image);
            News news = this.newsService.save(newsDTO);
            model.put("title", news.getTitle());
            model.put("body", news.getBody());
            return this.getNews(news.getMainCategory().getName(), news.getId(), model);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
            model.put("error", "Error al cargar noticia");
            return "index.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("error", ex.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/edit/{id}")
    public String editNews(@PathVariable String id, ModelMap model){

        List<NewsCategory> categories = this.categoryService.findAll();
        List<CategoriesFormDTO> categoriesDTO = new ArrayList<>();
        NewsForm form;

        try {
            News news = this.newsService.getById(id);
            List<NewsCategory> newsCategories = news.getCategories();
            System.out.println(newsCategories.size());
            for(NewsCategory c : newsCategories) {
                System.out.println(c.getName());
            }
            for (NewsCategory c : categories) {
                boolean checked;
                checked = newsCategories.contains(c);
                System.out.println("cat list: " + c.getName());
                System.out.println("cat: " + c.getName());
                System.out.println(checked);
                categoriesDTO.add(new CategoriesFormDTO(c.getName(), c.getId(), checked));
            }
            form = this.dto.newsForm(news, categoriesDTO);
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
            for (NewsCategory c : categories) {
                categoriesDTO.add(new CategoriesFormDTO(c.getName(), c.getId(), false));
            }
            form = this.dto.newsForm(null, categoriesDTO);
        }

        model.addAttribute("form", form);
        model.addAttribute("edit", "edit");
        return "form_news";

    }

    @PostMapping("/edit/{id}")
    public String postEditNews(@PathVariable String id,
                               @RequestParam String title,
                               @RequestParam String subtitle,
                               @RequestParam String imageCaption,
                               @RequestParam String body,
                               @RequestParam(required = false) List<String> categories,
                               @RequestParam String mainCategory, MultipartFile image, ModelMap model){

        if (categories == null) {
            categories = new ArrayList<>();
        }
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String author = auth.getName();
            Boolean isAdmin = auth.getAuthorities().contains("ROLE_"+Role.ADMIN);
            NewsEditReqDTO newsDTO = new NewsEditReqDTO(title, subtitle, imageCaption, body, categories, mainCategory, author, image, id, isAdmin);

            this.newsService.edit(newsDTO);

            model.put("message", "Noticia editada con exito");
            System.out.println("editada con exito");
        } catch (NotFoundException e) {
            model.put("message", "La noticia no existe");
            System.out.println("no found exe");
        } finally {
            System.out.println("Finally");
            return "redirect:/reporter/panel";
        }
    }

}
