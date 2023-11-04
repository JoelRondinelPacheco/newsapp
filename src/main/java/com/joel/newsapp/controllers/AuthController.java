package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.UserPostReqDTO;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, ModelMap model) {
        return "login.html";
    }

    @GetMapping("/register")
    public String register() { return "register.html"; }

    @PostMapping("/register/save")
    public String registro(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String confirmpassword, ModelMap model, MultipartFile archive) {
        if (!password.equals(confirmpassword)) {
            model.put("passworderror", "Las contraseñas no coinciden");
            return "register.html";
        }
        UserPostReqDTO userDTO = new UserPostReqDTO(name, email, password, archive);
        this.userService.save(userDTO);
        model.put("creado", "Usuario creado correctamente");
        return "index.html";
    }

    @PutMapping("/roles")
    public String roles(@RequestParam Role rol, @RequestParam Long id) throws NotFoundException {
        System.out.println(id);
        User u1 = this.userService.getById(id);
        System.out.println(u1);
        System.out.println("nombre antes dle cambio: " + u1.getName() + " rol: " + u1.getRole() + " id: " + u1.getId());
        User u = this.userService.roles(rol, id);
        System.out.println("nombre despues: " + u.getName() + " rol: " + u.getRole() + " id: " + u.getId());
        return "Rola cambiado a: " + rol.toString();

    }
}
