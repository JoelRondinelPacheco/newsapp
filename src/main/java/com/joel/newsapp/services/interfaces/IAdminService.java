package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.admin.AdminInfoDTO;
import com.joel.newsapp.dtos.admin.EditAdminDTO;
import com.joel.newsapp.dtos.admin.RegisterAdminDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IAdminService extends ICrudService<AdminInfoDTO, RegisterAdminDTO, EditAdminDTO, String> {
    String changeReporterRole(String userId, String newRole) throws NotFoundException;
    String changeUserRole(String userId, String newRole);
    String changeAdminRole(String userId, String newRole);
    UserInfoDTO createUser(RegisterUserDTO userDTO);
    ReporterInfoDTO createReporter(RegisterReporterDTO reporterDTO);
}
