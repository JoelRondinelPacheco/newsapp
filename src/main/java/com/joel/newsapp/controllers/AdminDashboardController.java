package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.FeaturedByCategoryDTO;
import com.joel.newsapp.dtos.news.NewsHomeDTO;
import com.joel.newsapp.dtos.news.NewsPaginatedDTO;
import com.joel.newsapp.dtos.newscategory.CategoryDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.search.AllNewsForm;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UsersPaginatedDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.*;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return this.users("user", 1, UserState.ACTIVE, model);
    }

    @GetMapping("/role/{role}")
    public String users(@PathVariable String role, @RequestParam(required = false) Integer page_number, @RequestParam UserState state, ModelMap model) {
        if (page_number == null) {
            page_number = 1;
        }
        model.addAttribute("role", role);
        model.addAttribute("state", state);
        if (role.equalsIgnoreCase(Role.USER.name())) {
            UsersPaginatedDTO usersPaginated = this.userService.getUsersByEnabledAndRole(page_number, 2, state, Role.USER);
            model.addAttribute("users", usersPaginated.getUsers());
            model.addAttribute("totalPages", usersPaginated.getTotalPages());
            model.addAttribute("currentPage", page_number);
            model.addAttribute("totalElements", usersPaginated.getTotalElements());
            return "admin_dashboard/admin_users";
        } else {
            Role roleEmployee;

            switch (role) {
                case "moderator":
                    roleEmployee = Role.MODERATOR;
                    break;
                case "admin":
                    roleEmployee = Role.ADMIN;
                    break;
                default:
                    roleEmployee = Role.REPORTER;
            }

            List<EmployeeDTO> employees = this.dashboardService.getAllEmployees(page_number, 10, roleEmployee, state);
            model.addAttribute("employees", employees);
            return "admin_dashboard/admin_employees";
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

        List<NewsHomeDTO> featured = this.newsService.getFeatured(10);
        if (featured.isEmpty()) {
            System.out.println("featued empty");
            model.addAttribute("featuredEmpty", "Featured empty");
        }
        model.addAttribute("featured", featured);
        for(NewsHomeDTO n : featured) {
            System.out.println(n.getNewsId() + " " + n.getNewsTitle());
        }

        //TODO MANJEAR CATEGORIAS VACIAS
        model.addAttribute("categories", categories);
        model.addAttribute("news", "featured");
        return "admin_dashboard/admin_news";
    }

    @GetMapping("/news/all")
    public String adminNewsAll(@RequestParam(required = false) String reporterName,
                               @RequestParam(required = false) String reporterLastname,
                               @RequestParam(required = false) String newsTitle,
                               @RequestParam(required = false) String newsDate,
                               @RequestParam(required = false) String newsCategory,
                               @RequestParam(required = false) Integer page_number,
                               ModelMap model) {

        System.out.println(page_number);
        if (page_number == null) {
            page_number = 1;
        }
        System.out.println(page_number);

        AllNewsForm form = AllNewsForm.builder()
                .reporterName(reporterName != null ? reporterName : "")
                .reporterLastname(reporterLastname != null ? reporterLastname : "")
                .newsTitle(newsTitle != null ? newsTitle : "")
                .newsDate(newsDate != newsDate ? newsDate : "")
                .newsCategory(newsTitle != null ? newsTitle : "")
                .build();

        NewsPaginatedDTO page = this.newsService.getAll(page_number,2);
        List<NewsHomeDTO> news = page.getNews();

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", page_number);
        model.addAttribute("totalElements", page.getTotalElements());

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

