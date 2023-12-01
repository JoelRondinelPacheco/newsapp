package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.AdminRegisterUserDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.*;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class ManageUsersController {
    @Autowired
    private IAdminManageUsers adminService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IModeratorService moderatorService;
    @Autowired
    private IUserRolesService userRolesService;

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
    @GetMapping("/{userId}/{oldRole}/{newRole}")
    public String changeRole(@PathVariable String userId, @PathVariable String oldRole, @PathVariable Role newRole, ModelMap model) {

        if (!newRole.name().equalsIgnoreCase(Role.USER.name()) && !newRole.name().equalsIgnoreCase(Role.REPORTER.name()) && !newRole.name().equalsIgnoreCase(Role.MODERATOR.name()) && !newRole.name().equalsIgnoreCase(Role.ADMIN.name())) {
            model.addAttribute("roleError", "No se proporciono un rol valido");
            // TODO HANDLE ERROR
            return "index";
        }
    try {
        String res = this.userRolesService.changeRole(userId, newRole);
        model.addAttribute("res", res);
        return "index";
    } catch (NotFoundException e) {
        System.out.println(e.getMessage());
        return "index";
    }
    }

}
