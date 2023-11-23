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
    public String changeRole(@PathVariable String userId, @PathVariable String oldRole, @PathVariable String newRole, ModelMap model) {

        if (!newRole.equalsIgnoreCase(Role.USER.name()) && !newRole.equalsIgnoreCase(Role.REPORTER.name()) && !newRole.equalsIgnoreCase(Role.MODERATOR.name()) && !newRole.equalsIgnoreCase(Role.ADMIN.name())) {
            model.addAttribute("roleError", "No se proporciono un rol valido");
            // TODO HANDLE ERROR
            return "index";
        }
    try {
        if (oldRole.equalsIgnoreCase("reporter")) {
            this.userRolesService.changeReporterRole(userId, newRole);
            return "index";
        } else if (oldRole.equalsIgnoreCase("moderator")) {
            this.userRolesService.changeModeratorRole(userId, newRole);
            return "index";
        } else if (oldRole.equalsIgnoreCase("admin")) {
            this.userRolesService.changeAdminRole(userId, newRole);
            return "index";
        } else if (oldRole.equalsIgnoreCase("user")) {
            /* this.userService.changeUserRole(userId, newRole);*/
            return "index";
        } else {
            model.addAttribute("roleError", "No se proporciono un rol valido");
            // TODO HANDLE ERROR
            return "index";
        }
    } catch (NotFoundException e) {
        System.out.println(e.getMessage());
        return "index";
    }
    }

}
