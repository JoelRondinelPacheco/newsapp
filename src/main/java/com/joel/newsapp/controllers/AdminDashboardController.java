package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class AdminDashboardController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IReporterService reporterService;
    @Autowired
    private INewsCategoryService categoryService;
    @Autowired
    private INewsService newsService;

    @GetMapping({"/", "", "/users"})
    public String adminDashboard(ModelMap model) {
        return this.users(Role.USER, UserState.ACTIVE, model);
    }

    @GetMapping("/{role}")
    public String users(@PathVariable Role role, @RequestParam UserState state, ModelMap model) {
        System.out.println("Role: " + role);
        System.out.println("State: " + state);
        List<UserInfoDTO> users = this.userService.getUsersByEnabledAndRole(state, role);
        model.addAttribute("users", users);
        model.addAttribute("role", role);
        model.addAttribute("state", state);
        switch (role) {
            case USER:
                return "admin_dashboard/admin_users";
            case REPORTER:
                return "admin_dashboard/admin_reporters";
            case MODERATOR:
                return "admin_dashboard/admin_moderators";
            case ADMIN:
                return "admin_dashboard/admin_dashboard";
            default:
                return "admin_dashboard/admin_users";
        }
    }

    @GetMapping("/categories")
    public String getAllCategories(ModelMap model) {
        List<NewsCategory> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin_dashboard/admin_categories";
    }

    @GetMapping("/register")
    public String adminPostUserForm() {
        return "admin_dashboard/admin_register";
    }

    @GetMapping("/news")
    public String adminNews(ModelMap model){
        // TODO Handle, all categories has one and only one featured news
        // TODO manage if returns more than one
        try {
            News mainFeatured = this.newsService.mainFeatured();
            model.addAttribute("mainFeatured", mainFeatured);
        } catch (NotFoundException e) {
            model.put("mainFeaturedError", e.getMessage());
        }
        List<News> featuredByCategory = this.newsService.allFeaturedByCategory();
        System.out.println(featuredByCategory.size());
        if (featuredByCategory.size() > 0) {
            model.addAttribute("featuredByCategory", featuredByCategory);
        } else {
            model.addAttribute("featuredError", "No hay noticias destacadas de las categorias");
        }
        return "admin_dashboard/admin_news";
    }

    @GetMapping("/news/all")
    public String adminNewsAll(ModelMap model) {
        List<News> news = this.newsService.getAll();
        if(news.size() > 0) {
            model.addAttribute("news", news);
        } else {
            model.addAttribute("allNewsError", "No hay noticias cargadas");
        }
        return "admin_dashboard/admin_news_all";
    }


}

