package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.Employee;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.EmployeePaginatedDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public EmployeePaginatedDTO getAllEmployees(int pageNumber, int pageSize, Role role, UserState state)  {
        List<EmployeeDTO> employees = new ArrayList<>();
        Pageable page = PageRequest.of(pageNumber - 1, pageSize);
        EmployeePaginatedDTO dto = new EmployeePaginatedDTO();
        switch (role) {
            case REPORTER:
                Page<Reporter> rep = Page.empty();
                switch (state) {
                    case ACTIVE:
                        System.out.println(role + " " + state);
                        rep = this.reporterRepository.findAllByEnabledAndUser_Verified(true, true, page);
                        break;
                    case INACTIVE:
                        rep = this.reporterRepository.findAllByEnabledAndUser_Verified(true, false, page);
                        break;
                    case BANNED:
                        rep = this.reporterRepository.findAllByEnabled(false, page);
                        break;
                }
                for (Reporter r : rep.getContent()) {
                    employees.add(this.dtos.createEmployeeInfo(r));
                }
                dto.setEmployees(employees);
                dto.setTotalPages(rep.getTotalPages());
                dto.setTotalElements(rep.getTotalElements());
                break;
            case ADMIN:
                Page<Admin> admins = Page.empty();
                switch (state) {
                    case ACTIVE:
                        admins = this.adminRepository.findAllByEnabledAndUser_Verified(true, true, page);
                        break;
                    case INACTIVE:
                        admins = this.adminRepository.findAllByEnabledAndUser_Verified(true, false, page);
                        break;
                    case BANNED:
                        admins = this.adminRepository.findAllByEnabled(false, page);
                        break;
                }

                for (Admin r : admins.getContent()) {
                    employees.add(this.dtos.createEmployeeInfo(r));
                }

                dto.setEmployees(employees);
                dto.setTotalPages(admins.getTotalPages());
                dto.setTotalElements(admins.getTotalElements());
                break;            case MODERATOR:
                Page<Moderator> moderators = Page.empty();
                switch (state) {
                    case ACTIVE:
                        moderators = this.moderatorRepository.findAllByEnabledAndUser_Verified(true, true, page);
                        break;
                    case INACTIVE:
                        moderators = this.moderatorRepository.findAllByEnabledAndUser_Verified(true, false, page);
                        break;
                    case BANNED:
                        moderators = this.moderatorRepository.findAllByEnabled(false, page);
                        break;
                }
                for (Moderator m : moderators.getContent()) {
                    employees.add(this.dtos.createEmployeeInfo(m));
                }

                dto.setEmployees(employees);
                dto.setTotalPages(moderators.getTotalPages());
                dto.setTotalElements(moderators.getTotalElements());
                break;
        }
        return dto;
    }
}
