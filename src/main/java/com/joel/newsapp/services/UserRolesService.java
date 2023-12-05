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
import com.joel.newsapp.services.interfaces.*;
import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService implements IUserRolesService {
    @Autowired private IUserService userService;
    @Autowired private IReporterService reporterService;
    @Autowired private IAdminService adminService;
    @Autowired private IModeratorService moderatorService;
    @Autowired private IUserRepository userRepository;
    @Autowired private IReporterRepository reporterRepository;
    @Autowired private IAdminRepository adminRepository;
    @Autowired private IModeratorRepository moderatorRepository;

    @Override
    public String changeRole(String userId, Role newRole) throws NotFoundException {
        User user = this.userService.findById(userId);
        if (user.getRole() == newRole) { return "Same role"; }

        if (user.getRole() == Role.REPORTER) {
            Reporter reporter = this.reporterService.findByUserId(userId);
            reporter.setEnabled(false);
            this.reporterRepository.save(reporter);
        }

        if (user.getRole() == Role.ADMIN) {
            Admin admin = this.adminService.findByUserId(userId);
            this.adminRepository.delete(admin);
        }

        if (user.getRole() == Role.MODERATOR) {
            Moderator mod = this.moderatorService.findByUserId(userId);
            this.moderatorRepository.delete(mod);
        }

        switch (newRole) {
            case USER:
                user.setRole(Role.USER);
                this.userRepository.save(user);
                return "Role changed from Reporter to User";
            case REPORTER:
                user.setRole(Role.REPORTER);
                Reporter reporter = Reporter.builder()
                        .user(user)
                        .monthlySalary(1D)
                        .enabled(true)
                        .build();
                this.userRepository.save(user);
                this.reporterRepository.save(reporter);
                return "Role changed from User to Reporter";
            case ADMIN:
                user.setRole(Role.ADMIN);
                this.userRepository.save(user);
                Admin admin = Admin.builder()
                        .user(user)
                        .monthlySalary(100D)
                        .enabled(true)
                        .build();
                this.adminRepository.save(admin);
                return "Role changed from Reporter to Admin";
            case MODERATOR:
                user.setRole(Role.MODERATOR);
                this.userRepository.save(user);
                Moderator mod = Moderator.builder()
                        .user(user)
                        .monthlySalary(100D)
                        .enabled(true)
                        .build();
                this.moderatorRepository.save(mod);
                return "Role changed from Reporter to Moderator";
        }
        return "Invalid Role";

    }

}
