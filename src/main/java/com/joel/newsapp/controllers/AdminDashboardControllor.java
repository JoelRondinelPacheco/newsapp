package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class AdminDashboardControllor {
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String usersPanel(ModelMap model) {
        List<UserInfoDTO> users = this.userService.getAllUsers();;
        model.addAttribute("users", users);
        return "/admin_dashboard/admin_dashboard";
    }

    @GetMapping("/{role}")
    public String clientsActive(@PathVariable String role, @RequestParam Boolean active, ModelMap model) {
            switch (role) {
                case 'clients':
                    List<UserInfoDTO> users = this.userService.getUsersByEnabledAndRole(active, Role.USER);
                    model.addAttribute("users", users);
                    model.addAttribute("active", active);
                    return "/admin_dashboard/admin_users";
                    break;
                case 'reporters':
                    break;
                    case 'moderators'
                        List<UserInfoDTO> moderators = this.userService.getUsersByEnabledAndRole(active, Role.MODERATOR);
                        model.addAttribute("moderators", moderators);

                        break;
                case 'admins':
                    List<UserInfoDTO> admins = this.userService.getUsersByEnabledAndRole(active, Role.ADMIN);
                    model.addAttribute("admins", admins);

                    break;
                default:

            }
        }
    }
}
