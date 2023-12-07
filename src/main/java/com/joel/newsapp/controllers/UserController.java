package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.EditUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UserProfileInfoDTO;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.ImageService;
import com.joel.newsapp.services.NewsService;
import com.joel.newsapp.services.UserService;
import com.joel.newsapp.utils.BuildDTOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired private IUserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private ImageService imageService;
    @Autowired private NewsService newsService;
    @Autowired private BuildDTOs dto;

    @GetMapping("/public-img")
    public ResponseEntity<byte[]> userImage () throws NotFoundException {
        byte[] image = this.imageService.getById("20e1392a-4bc6-433a-a114-361c534c02b5").getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image,headers, HttpStatus.OK);
    }

    @GetMapping("/panel")
    public String panel(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try {
            UserProfileInfoDTO user = this.userService.userProfileInfo(username);
            model.addAttribute("user", user);
            return "user_panel.html";
        } catch (NotFoundException e) {
            model.put("error", e.getMessage());
            return "user_panel.html";
        }
    }

    @PostMapping("/edit/{id}")
    public String postEdit(@PathVariable String id, @RequestParam(required = false) MultipartFile archive, @RequestParam String name, @RequestParam String lastname, @RequestParam String display_name, ModelMap model)  {
        EditUserDTO edit = new EditUserDTO(id, name, lastname, display_name, archive);
        try {
            UserInfoDTO user = this.userService.edit(edit);
            model.addAttribute("success", "Información de usuario actualizada");
            return this.panel(model);
        } catch (NotFoundException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return this.panel(model);
        }
    }
}
