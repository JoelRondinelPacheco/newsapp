package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ReporterService reporterService;
    @Autowired
    private NewsCategoryService categoryService;

    @GetMapping("/news")
    public String newsPanel(ModelMap model) throws NotFoundException {
        List<News> news = this.newsService.getAll();
        model.addAttribute("noticias", news);
        return "newspanel.html";
    }

    @GetMapping("/dashboard")
    public String usersPanel(ModelMap model) {
        List<UserInfoDTO> users = this.userService.getAllUsers();;
        model.addAttribute("users", users);
        return "/admin_dashboard/admin_dashboard";
    }

    @GetMapping("/dashboard/reporters")
    public String getAllReporters(ModelMap model) {
        List<ReporterInfoDTO> reporters = this.reporterService.getAllReporters();
        model.addAttribute("reporters", reporters);
        return "/admin_dashboard/admin_reporters";
    }

    @GetMapping("/dashboard/categories")
    public String getAllCategories(ModelMap model) {
        List<NewsCategory> categories = this.categoryService.findAll();
        model.addAttribute("categories", categories);
        return "/admin_dashboard/admin_categories";
    }

    @PostMapping("/category/save")
    public String createCategory(@RequestParam String category, ModelMap model) {
        NewsCategory cat = this.categoryService.save(category);
        model.put("success", "Category saved");
        return this.getAllCategories(model);
    }

    @GetMapping("/image")
    public String imageForm(){
        return "postimage.html";
    }

    @PostMapping("/image")
    public String postImage(MultipartFile archive, ModelMap model) throws IOException {
        Image image = this.imageService.save(archive);

        model.put("image", "Imagen cargada");
        return "postimage.html";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable String id, @RequestParam String salary, @RequestParam String active, ModelMap model){
        String response = this.reporterService.updateSalaryAndEnabled(Integer.parseInt(salary), (active.equals("true") ? true : false), id);
        model.put("response", response);
        return "index.html";
    }

    @GetMapping("/register")
    public String adminPostUser() {
        return "/admin_dashboard/admin_register";
    }
}
