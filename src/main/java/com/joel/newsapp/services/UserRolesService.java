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
    public String changeRole(String userId, Role newRole, Double salary) throws NotFoundException {
        User user = this.userService.findById(userId);
        if (user.getRole() == newRole) { return "Same role"; }

        if (user.getRole() == Role.REPORTER) {
            try {
                Reporter reporter = this.reporterService.findByUserId(userId);
                reporter.setEnabled(false);
                this.reporterRepository.save(reporter);
            } catch (NotFoundException e) {}
        }

        if (user.getRole() == Role.ADMIN) {
            try {
                Admin admin = this.adminService.findByUserId(userId);
                admin.setEnabled(false);
                this.adminRepository.save(admin);
            } catch (NotFoundException e) {}
        }

        if (user.getRole() == Role.MODERATOR) {
            try {
                Moderator mod = this.moderatorService.findByUserId(userId);
                mod.setEnabled(false);
                this.moderatorRepository.save(mod);
            } catch (NotFoundException e) {}
        }

        switch (newRole) {
            case USER:
                user.setRole(Role.USER);
                this.userRepository.save(user);
                return "Role changed from Reporter to User";
            case REPORTER:
                user.setRole(Role.REPORTER);
                Reporter reporterR;
                try {
                    reporterR = this.reporterService.findByUserId(userId);
                    reporterR.setMonthlySalary(salary);
                    reporterR.setEnabled(true);
                } catch (NotFoundException e) {
                    reporterR = Reporter.builder()
                            .user(user)
                            .monthlySalary(salary)
                            .enabled(true)
                            .build();
                }
                this.reporterRepository.save(reporterR);
                this.userRepository.save(user);
                return "Role changed from User to Reporter";
            case ADMIN:
                user.setRole(Role.ADMIN);
                Admin adminA;
                try {
                    adminA = this.adminService.findByUserId(userId);
                    adminA.setMonthlySalary(salary);
                    adminA.setEnabled(true);
                } catch (NotFoundException e) {
                    adminA = Admin.builder()
                            .user(user)
                            .monthlySalary(salary)
                            .enabled(true)
                            .build();
                }
                this.userRepository.save(user);
                this.adminRepository.save(adminA);
                return "Role changed from Reporter to Admin";
            case MODERATOR:
                user.setRole(Role.MODERATOR);
                Moderator moderatorM;

                try {
                    moderatorM = this.moderatorService.findByUserId(userId);
                    moderatorM.setMonthlySalary(salary);
                    moderatorM.setEnabled(true);
                } catch (NotFoundException e) {
                    moderatorM = Moderator.builder()
                            .user(user)
                            .monthlySalary(salary)
                            .enabled(true)
                            .build();
                }
                this.userRepository.save(user);
                this.moderatorRepository.save(moderatorM);
                return "Role changed from Reporter to Moderator";
        }
        return "Invalid Role";

    }

}
