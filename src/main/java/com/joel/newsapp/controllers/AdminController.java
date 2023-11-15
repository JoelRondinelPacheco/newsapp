package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.AdminRegisterReporterDTO;
import com.joel.newsapp.dtos.users.AdminRegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.*;
import com.joel.newsapp.utils.Role;
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
    public String adminPostUserForm() {
        return "/admin_dashboard/admin_register";
    }

    @PostMapping("/register")
    public String adminPostUser(@RequestParam String name,
                                @RequestParam String lastname,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmpassword,
                                @RequestParam Role rol,
                                @RequestParam(required = false) Double monthlySalary,
                                ModelMap model) {

        if (!password.equals(confirmpassword)) {
            System.out.println("Error password");
        }
        if(rol.equals(Role.REPORTER)) {
            AdminRegisterReporterDTO reporter = new AdminRegisterReporterDTO(name, lastname, email, password, rol, monthlySalary);
            this.reporterService.adminRegister(reporter);
            model.put("success", "Reporter created successfully");
            return "redirect:/";
        } else if (rol.equals(Role.USER) || rol.equals(Role.MODERATOR) || rol.equals(Role.ADMIN)) {
            AdminRegisterUserDTO user = new AdminRegisterUserDTO(name, lastname, email, password, rol);
            this.userService.adminRegister(user);
            model.put("success", "User created successfully");
            return "redirect:/";
        } else {
            System.out.println("Rol not valid");
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId) {
        try {
            this.userService.adminActiveState(userId, false);
            return "redirect:/";
        } catch (NotFoundException e) {
            return "redirect:/";

        }
    }

    @GetMapping("/activate/{userId}")
    public String activateUser(@PathVariable String userId) {
        try {
            this.userService.adminActiveState(userId, false);
            return "redirect:/";
        } catch (NotFoundException e) {
            return "redirect:/";
        }
    }
}
