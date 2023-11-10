package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.ImageService;
import com.joel.newsapp.services.NewsService;
import com.joel.newsapp.services.ReporterService;
import com.joel.newsapp.services.UserService;
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

    @GetMapping("/news")
    public String newsPanel(ModelMap model) throws NotFoundException {
        List<News> news = this.newsService.getAll();
        model.addAttribute("noticias", news);
        return "newspanel.html";
    }

    @GetMapping("/users")
    public String usersPanel(ModelMap model) {
        List<User> reporters = this.userService.getAllReporters();;
        model.addAttribute("reporters", reporters);
        return "adminuserspanel.html";
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
}
