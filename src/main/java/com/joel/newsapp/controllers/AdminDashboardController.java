package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.FeaturedByCategoryDTO;
import com.joel.newsapp.dtos.news.NewsHomeDTO;
import com.joel.newsapp.dtos.newscategory.CategoryDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.search.AllNewsForm;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.*;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping("/dashboard")
public class AdminDashboardController {
    @Autowired private IUserService userService;
    @Autowired private IReporterService reporterService;
    @Autowired private INewsCategoryService categoryService;
    @Autowired private INewsService newsService;
    @Autowired private IDashboardService dashboardService;

    @GetMapping({"/", "", "/users"})
    public String adminDashboard(ModelMap model) {
        return this.users(Role.USER, UserState.ACTIVE, model);
    }

    @GetMapping("/role/{role}")
    public String users(@PathVariable Role role, @RequestParam UserState state, ModelMap model) {
        model.addAttribute("role", role);
        model.addAttribute("state", state);
        if (role == Role.USER) {
            List<UserInfoDTO> users = this.userService.getUsersByEnabledAndRole(state, role);
            model.addAttribute("users", users);
            return "admin_dashboard/admin_users";
        } else {
            List<EmployeeDTO> employees = this.dashboardService.getAllEmployees(role, state);
            model.addAttribute("employees", employees);
            return "admin_dashboard/admin_employees";
            /*if (role == Role.REPORTER) {
                return "admin_dashboard/admin_reporters";
            } else if (role == Role.ADMIN) {
                return "admin_dashboard/admin_admins";
            } else {
                return "admin_dashboard/admin_moderators";
            }*/
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
            NewsHomeDTO mainFeatured = this.newsService.mainFeatured();
            model.addAttribute("mainFeatured", mainFeatured);
        } catch (NotFoundException e) {
            model.put("mainFeaturedError", e.getMessage());
        }

        List<NewsCategory> categories = this.categoryService.findAll();

        List<FeaturedByCategoryDTO> featuredByCategory = this.newsService.allFeaturedByCategory();
        if (!featuredByCategory.isEmpty()) {
            model.addAttribute("featuredByCategory", featuredByCategory);
        } else {
            model.addAttribute("featuredError", "No hay categorias cargadas");
        }

        List<News> latest = this.newsService.latest(5);
        if (latest.isEmpty()) {
            model.addAttribute("latestEmpty", true);
        }

        //TODO MANJEAR CATEGORIAS VACIAS
        model.addAttribute("categories", categories);
        model.addAttribute("news", "featured");
        return "admin_dashboard/admin_news";
    }

    @GetMapping("/news/all")
    public String adminNewsAll(@RequestParam(required = false) String reporterName, @RequestParam(required = false) String reporterLastname, @RequestParam(required = false) String newsTitle, @RequestParam(required = false) String newsDate, @RequestParam(required = false) String newsCategory, ModelMap model) {

        AllNewsForm form = AllNewsForm.builder()
                .reporterName(reporterName != null ? reporterName : "")
                .reporterLastname(reporterLastname != null ? reporterLastname : "")
                .newsTitle(newsTitle != null ? newsTitle : "")
                .newsDate(newsDate != newsDate ? newsDate : "")
                .newsCategory(newsTitle != null ? newsTitle : "")
                .build();

        List<NewsHomeDTO> news = this.newsService.getAll();
        List<CategoryDTO> categories = this.categoryService.findAllDTO();
        if(news.size() > 0) {
            model.addAttribute("listNews", news);
        } else {
            model.addAttribute("allNewsError", "No hay noticias cargadas");
        }
        System.out.println("noticias");
        System.out.println(news.size());
        model.addAttribute("news", "all");
        model.addAttribute("categories", categories);
        model.addAttribute("form", form);
        return "admin_dashboard/admin_news_all";
    }


}

