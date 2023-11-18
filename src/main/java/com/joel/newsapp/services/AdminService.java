package com.joel.newsapp.services;

import com.joel.newsapp.dtos.admin.AdminInfoDTO;
import com.joel.newsapp.dtos.admin.EditAdminDTO;
import com.joel.newsapp.dtos.admin.RegisterAdminDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IReporterService reporterService;
    @Autowired
    private UserService userService;

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
    @Override
    public String changeReporterRole(String userId, String newRole) throws NotFoundException {
        Reporter reporter = this.reporterService.findById(userId);
        /*
        TODO IMPLEMENT PATRON STRATEGY
        Check newRole:
            if User -> Change Role in User, disable entity Reporter

            if admin -> Change role in User, disable entity Reporter, new Admin

            if

         */
        return null;
    }

    @Override
    public String changeUserRole(String userId, String newRole) {
        return null;

    }

    @Override
    public String changeAdminRole(String userId, String newRole) {
        return null;

    }

    @Override
    public UserInfoDTO createUser(RegisterUserDTO userDTO) {
        return null;
    }

    @Override
    public ReporterInfoDTO createReporter(RegisterReporterDTO reporterDTO) {
        return null;
    }

}
