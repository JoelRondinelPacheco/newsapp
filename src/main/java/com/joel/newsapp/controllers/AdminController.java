package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.*;
import com.joel.newsapp.services.interfaces.IAdminManageUsers;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired private UserService userService;
    @Autowired private NewsService newsService;
    @Autowired private ImageService imageService;
    @Autowired private ReporterService reporterService;
    @Autowired private NewsCategoryService categoryService;
    @Autowired private AdminDashboardController dashboardControllor;
    @Autowired private IAdminManageUsers adminService;


    @PostMapping("/category/save")
    public String createCategory(@RequestParam String category, ModelMap model) {
        NewsCategory cat = this.categoryService.save(category);
        model.put("success", "Category saved");
        return this.dashboardControllor.getAllCategories(model);
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

    @GetMapping("/user/active/{userId}")
    public String setEnabledUser(@PathVariable String userId, @RequestParam Boolean active, @RequestParam Integer pagenumber, @RequestParam String role, @RequestParam UserState state) {
        try {
            System.out.println(active);
            this.adminService.adminEnabledState(userId, active);
            return "redirect:/dashboard/role/" + role + "?page_number=" + pagenumber + "&state=" + state;
        } catch (NotFoundException e) {
            return "redirect:/";
        }
    }

    @GetMapping("/employee/active/{employeeId}")
    public String setEnabledEmployee(@PathVariable String employeeId, @RequestParam Boolean active, @RequestParam Integer pagenumber, @RequestParam String role, @RequestParam UserState state){
        try {
            this.adminService.setEnabledEmployee(employeeId, active, role);
            return "redirect:/dashboard/role/" + role + "?page_number=" + pagenumber + "&state=" + state;
        } catch (NotFoundException e) {
            System.out.println("exption");
            return "redirect:/dashboard/role/" + role + "?page_number=" + pagenumber + "&state=" + state;
        }
    }

    @GetMapping("/news/main/{newsId}")
    public String setMainFeatured(@PathVariable String newsId, ModelMap model) {
        try {
            News news = this.newsService.setMainFeatured(newsId);
            model.addAttribute("mainFeaturedUpdated", news);
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }

       // return "redirect:admin_dashboard/admin_news";
        return "redirect:/dashboard/news";
    }

    @GetMapping("/news/category/{newsId}")
    public String setCategoryFeatured(@PathVariable String newsId, ModelMap model) {
        try {
            News news = this.newsService.setCategoryFeatured(newsId);
            model.addAttribute("mainFeaturedUpdated", news);
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/dashboard/news";
    }

    @GetMapping("/news/featured/{newsId}")
    public String setIsFeatured(@PathVariable String newsId, @RequestParam Boolean is_featured, ModelMap model) {
        try {
            News news = this.newsService.setIsFeatured(newsId, is_featured);
            model.addAttribute("featuredUpdated", news);
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/dashboard/news";
    }

}
