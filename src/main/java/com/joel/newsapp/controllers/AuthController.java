package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.services.interfaces.IAdminManageUsers;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeController homeController;
    @Autowired
    private IAdminManageUsers manageUsers;

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, ModelMap model) {
        return "login.html";
    }

    @GetMapping("/register")
    public String register() { return "register.html"; }

    @PostMapping("/register/save")
    public String registro(@RequestParam String name, @RequestParam  String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String confirmpassword, ModelMap model, MultipartFile archive) {
        if (!password.equals(confirmpassword)) {
            model.put("passworderror", "Las contraseñas no coinciden");
            return "register.html";
        }
        RegisterUserDTO userDTO = new RegisterUserDTO(name, lastname, email, password, archive, Role.USER);
        this.userService.register(userDTO);
        model.put("creado", "Usuario creado correctamente");
        System.out.println("enotr register");
        //Todo post register page
        return "redirect:/";
    }

    //SETER PASSWORD
    @GetMapping("/register/set/{token}")
    public String setPassword(@PathVariable String token) {
        return "set_password";
    }

    @PostMapping("/register/set/{token}")
    public String postSetPassword(@RequestParam String password, @RequestParam String confirmPassword, ModelMap model) {


    }

}
