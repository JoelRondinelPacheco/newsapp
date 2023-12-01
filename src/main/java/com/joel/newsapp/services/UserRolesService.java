package com.joel.newsapp.services;

import com.joel.newsapp.entities.Admin;
import com.joel.newsapp.entities.Moderator;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IAdminRepository;
import com.joel.newsapp.repositories.IModeratorRepository;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.services.interfaces.IUserRolesService;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService implements IUserRolesService {
    @Autowired private IUserService userService;
    @Autowired private IReporterService reporterService;
    @Autowired private IUserRepository userRepository;
    @Autowired private IReporterRepository reporterRepository;
    @Autowired private IAdminService adminService;
    @Autowired private IAdminRepository adminRepository;
    @Autowired private IModeratorRepository moderatorRepository;
    @Override
    public String changeReporterRole(String userId, String newRole) throws NotFoundException {
        User user = this.userService.findById(userId);
        Reporter reporter = this.reporterService.findByUserId(userId);

        switch (newRole.toLowerCase()) {
            case "user":
                reporter.setEnabled(false);
                user.setRole(Role.USER);
                this.reporterRepository.save(reporter);
                this.userRepository.save(user);
                return "Role changed from Reporter to User";

            case "admin":
                reporter.setEnabled(false);
                user.setRole(Role.ADMIN);
                this.userRepository.save(user);
                Admin admin = Admin.builder()
                        .user(user)
                        .monthlySalary(100D)
                        .enabled(true)
                        .build();
                this.adminRepository.save(admin);
                this.reporterRepository.save(reporter);
                return "Role changed from Reporter to Admin";
            case "moderator":
                reporter.setEnabled(false);
                user.setRole(Role.MODERATOR);
                this.userRepository.save(user);
                Moderator mod = Moderator.builder()
                        .user(user)
                        .monthlySalary(100D)
                        .enabled(true)
                        .build();
                this.moderatorRepository.save(mod);
                this.reporterRepository.save(reporter);
                return "Role changed from Reporter to Moderator";
            case "editor":
                return "Role changed from Reporter to Editor";
        }
        return "Invalid Role";
    }

    @Override
    public String changeUserRole(String userId, String newRole) throws NotFoundException {
        return null;
    }

    @Override
    public String changeAdminRole(String userId, String newRole) throws NotFoundException  {
        return null;
    }

    @Override
    public String changeModeratorRole(String userId, String newRole) throws NotFoundException {
        return null;
    }
}
