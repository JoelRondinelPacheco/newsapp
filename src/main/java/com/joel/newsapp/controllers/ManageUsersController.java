package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.IAdminManageUsers;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IModeratorService;
import com.joel.newsapp.services.interfaces.IUserService;
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

    @PostMapping("/register")
    public String adminPostUser(@RequestParam String name,
                                @RequestParam String lastname,
                                @RequestParam String email,
                                @RequestParam Role rol,
                                @RequestParam(required = false) Double monthlySalary,
                                ModelMap model) {

        AdminRegisterEmployeeDTO employee = new AdminRegisterEmployeeDTO(name, lastname, email, rol);
        if (rol != null) {
            employee.setRole(rol);
        }
        this.adminService.createEmployee(employee);
        return "index";

    }

    @GetMapping("/{userId}/{oldRole}/{newRole}")
    public String changeRole(@PathVariable String userId, @PathVariable String oldRole, @PathVariable String newRole, ModelMap model) {

        if (!newRole.equalsIgnoreCase(Role.USER.name()) && !newRole.equalsIgnoreCase(Role.REPORTER.name()) && !newRole.equalsIgnoreCase(Role.MODERATOR.name()) && !newRole.equalsIgnoreCase(Role.ADMIN.name())) {
            model.addAttribute("roleError", "No se proporciono un rol valido");
            // TODO HANDLE ERROR
            return "index";
        }

        if (oldRole.equalsIgnoreCase("reporter")) {
            try {
                this.adminService.changeReporterRole(userId, newRole);
                return "index";
            } catch (NotFoundException e) {
                return "index";
            }
        } else if (oldRole.equalsIgnoreCase("moderator")) {
            this.moderatorService.changeModeratorRole(userId, newRole);
            return "index";
        } else if (oldRole.equalsIgnoreCase("admin")) {
            this.adminService.changeAdminRole(userId, newRole);
            return "index";
        } else if (oldRole.equalsIgnoreCase("user")) {
            this.userService.changeUserRole(userId, newRole);
            return "index";
        } else {
            model.addAttribute("roleError", "No se proporciono un rol valido");
            // TODO HANDLE ERROR
            return "index";
        }
    }

}
