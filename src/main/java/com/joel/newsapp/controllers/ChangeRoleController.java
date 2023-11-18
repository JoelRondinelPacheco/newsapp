package com.joel.newsapp.controllers;

import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IModeratorService;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class ChangeRoleController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IModeratorService moderatorService;

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
