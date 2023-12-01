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
    public List<EmployeeDTO> getAllEmployees(Role role)  {
        List<EmployeeDTO> employees = new ArrayList<>();
        switch (role) {
            case REPORTER:
                List<Reporter> rep = this.reporterRepository.findAll();
                for (Reporter r : rep) {
                    employees.add(this.dtos.createEmployeeInfo(r));
                }
                break;
            case ADMIN:
                List<Admin> admins = this.adminRepository.findAll();
                for (Admin a : admins) {
                    employees.add(this.dtos.createEmployeeInfo(a));
                }
                break;
            case MODERATOR:
                List<Moderator> moderators = this.moderatorRepository.findAll();
                for (Moderator m : moderators) {
                    employees.add(this.dtos.createEmployeeInfo(m));
                }
        }
        return employees;
    }
}
