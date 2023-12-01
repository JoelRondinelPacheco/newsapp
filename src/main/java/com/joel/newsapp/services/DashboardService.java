package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.Employee;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.entities.Admin;
import com.joel.newsapp.entities.Moderator;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IAdminRepository;
import com.joel.newsapp.repositories.IModeratorRepository;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IDashboardService;
import com.joel.newsapp.services.interfaces.IModeratorService;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.utils.BuildDTOs;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService implements IDashboardService {
    @Autowired private IReporterService reporterService;
    @Autowired private IReporterRepository reporterRepository;
    @Autowired private IAdminService adminService;
    @Autowired private IAdminRepository adminRepository;
    @Autowired private IModeratorService moderatorService;
    @Autowired private IModeratorRepository moderatorRepository;
    @Autowired private BuildDTOs dtos;

    @Override
    public EmployeeDTO getEmployee(String id, Role role) throws NotFoundException {
        return null;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees(Role role, UserState state)  {
        System.out.println(role);
        List<EmployeeDTO> employees = new ArrayList<>();
        switch (role) {
            case REPORTER:
                List<Reporter> rep = new ArrayList<>();
                switch (state) {
                    case ACTIVE:
                        rep = this.reporterRepository.findAllByEnabledAndUser_Verified(true, true);
                        break;
                    case INACTIVE:
                        rep = this.reporterRepository.findAllByEnabledAndUser_Verified(true, false);
                        break;
                    case BANNED:
                        rep = this.reporterRepository.findAllByEnabled(false);
                        break;
                }
                for (Reporter r : rep) {
                    employees.add(this.dtos.createEmployeeInfo(r));
                }
                break;
            case ADMIN:
                List<Admin> admins = new ArrayList<>();
                switch (state) {
                    case ACTIVE:
                        admins = this.adminRepository.findAllByEnabledAndUser_Verified(true, true);
                        break;
                    case INACTIVE:
                        admins = this.adminRepository.findAllByEnabledAndUser_Verified(true, false);
                        break;
                    case BANNED:
                        admins = this.adminRepository.findAllByEnabled(false);
                        break;
                }

                for (Admin r : admins) {
                    employees.add(this.dtos.createEmployeeInfo(r));
                }
                break;
            case MODERATOR:
                List<Moderator> moderators = new ArrayList<>();
                switch (state) {
                    case ACTIVE:
                        moderators = this.moderatorRepository.findAllByEnabledAndUser_Verified(true, true);
                        break;
                    case INACTIVE:
                        moderators = this.moderatorRepository.findAllByEnabledAndUser_Verified(true, false);
                        break;
                    case BANNED:
                        moderators = this.moderatorRepository.findAllByEnabled(false);
                        break;
                }
                for (Moderator m : moderators) {
                    employees.add(this.dtos.createEmployeeInfo(m));
                }
                break;
        }
        return employees;
    }
}
