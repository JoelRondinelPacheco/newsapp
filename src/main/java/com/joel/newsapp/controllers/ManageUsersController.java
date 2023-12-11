package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.AdminRegisterUserDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.*;
import com.joel.newsapp.utils.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class ManageUsersController {
    @Autowired private IAdminManageUsers adminService;
    @Autowired private IUserService userService;
    @Autowired private IModeratorService moderatorService;
    @Autowired private IUserRolesService userRolesService;

    @PostMapping("/register")
    public String adminPostUser(@RequestParam String name,
                                @RequestParam String lastname,
                                @RequestParam String email,
                                @RequestParam Role role,
                                @RequestParam(required = false) Double monthlySalary,
                                ModelMap model) {

        if (this.userService.checkUserEmail(email)) {
            model.addAttribute("emailError", "Email already registered");
        }
        AdminRegisterUserDTO user = new AdminRegisterUserDTO(name, lastname, email, role);
        if ( role == Role.USER) {
            this.adminService.createUser(user);
        } else {
            AdminRegisterEmployeeDTO employee = new AdminRegisterEmployeeDTO(monthlySalary, user);
            this.adminService.createEmployee(employee);
        }
        return "index";

    }
    //CAMBIAR ROLE
    @PostMapping("/edit/{userId}")
    public String changeRole(@PathVariable String userId, @RequestParam(required = false) Role role, @RequestParam(required = false) Double salary, ModelMap model, HttpServletRequest req) {
        System.out.println(role + " | " + salary);
        if (salary == null) {
            salary = 0D;
        }
   try {
        String res = this.userRolesService.changeRole(userId, role, salary);
        model.addAttribute("res", res);
       System.out.println(req.getHeader("Referer"));
       return "redirect:/";
    } catch (NotFoundException e) {
        System.out.println(e.getMessage());
        return "redirect:/";
    }
    }

}
