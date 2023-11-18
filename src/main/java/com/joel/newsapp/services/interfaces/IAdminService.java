package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.admin.AdminInfoDTO;
import com.joel.newsapp.dtos.admin.EditAdminDTO;
import com.joel.newsapp.dtos.admin.RegisterAdminDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IAdminService extends ICrudService<AdminInfoDTO, RegisterAdminDTO, EditAdminDTO, String> {

}
