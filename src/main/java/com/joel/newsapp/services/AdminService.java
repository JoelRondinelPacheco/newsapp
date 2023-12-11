package com.joel.newsapp.services;

import com.joel.newsapp.dtos.admin.AdminInfoDTO;
import com.joel.newsapp.dtos.admin.EditAdminDTO;
import com.joel.newsapp.dtos.admin.RegisterAdminDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.entities.Admin;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IAdminRepository;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired private IReporterService reporterService;
    @Autowired private UserService userService;
    @Autowired private IAdminRepository adminRepository;

    @Override
    public AdminInfoDTO save(RegisterAdminDTO registerAdminDTO) throws Exception {
        return null;
    }

    @Override
    public AdminInfoDTO getById(String s) throws NotFoundException {
        return null;
    }

    @Override
    public AdminInfoDTO edit(EditAdminDTO editAdminDTO) throws Exception {
        return null;
    }

    @Override
    public String deleteById(String s) throws NotFoundException {
        return null;
    }

    public ReporterInfoDTO createReporter(RegisterReporterDTO reporterDTO) {
        return null;
    }

    @Override
    public Admin findByUserId(String userId) throws NotFoundException {
        Optional<Admin> admin = this.adminRepository.findByUser_Id(userId);
        if (admin.isPresent()) {
            return admin.get();
        }
        throw new NotFoundException("Admin not found");
    }

    @Override
    public Admin findById(String employeeId) throws NotFoundException {
        Optional<Admin> adminOptional = this.adminRepository.findById(employeeId);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        }
        throw new NotFoundException("Admin not found");
    }
}
