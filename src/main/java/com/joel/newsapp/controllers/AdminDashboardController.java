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
        return this.users("clients", true, model);
    }

    @GetMapping("/{role}")
    public String users(@PathVariable String role, @RequestParam Boolean active, ModelMap model) {
        switch (role) {
            case "clients":
                List<UserInfoDTO> users = this.userService.getUsersByEnabledAndRole(active, Role.USER);
                for (UserInfoDTO u : users) {
                    System.out.println(u.getName() + " " + u.getLastname() + " " + u.getRole() + " " + u.getEmail());
                }
                System.out.println("Clients");
                System.out.println(active);
                System.out.println(role);
                System.out.println(users.size());
                model.addAttribute("users", users);
                model.addAttribute("active", active);
                return "admin_dashboard/admin_users";
            case "reporters":
                List<ReporterInfoDTO> reporters = this.reporterService.getAllReporters();
                model.addAttribute("reporters", reporters);
                model.addAttribute("active", active);
                return "admin_dashboard/admin_reporters";
            case "moderators":
                List<UserInfoDTO> moderators = this.userService.getUsersByEnabledAndRole(active, Role.MODERATOR);
                model.addAttribute("moderators", moderators);
                model.addAttribute("active", active);
                return "admin_dashboard/admin_moderators";
            case "admins":
                List<UserInfoDTO> admins = this.userService.getUsersByEnabledAndRole(active, Role.ADMIN);
                for (UserInfoDTO u : admins) {
                    System.out.println(u.getName() + " " + u.getLastname() + " " + u.getRole() + " " + u.getEmail());
                }
                model.addAttribute("admins", admins);
                model.addAttribute("active", active);
                System.out.println("admins");
                System.out.println(active);
                System.out.println(role);
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


}

