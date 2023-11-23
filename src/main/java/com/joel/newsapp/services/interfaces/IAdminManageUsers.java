package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.password.PasswordDTO;
import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.AdminRegisterUserDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IAdminManageUsers {
    String createEmployee(AdminRegisterEmployeeDTO employee);
    String createUser(AdminRegisterUserDTO userDTO);
    String setPassword(PasswordDTO password) throws NotFoundException;
    String resetPassword(PasswordDTO password) throws  NotFoundException;
    String changeReporterRole(String userId, String newRole) throws NotFoundException;
    User adminRegisterUser(AdminRegisterUserDTO userDTO);
    String adminEnabledState(String id, Boolean state) throws NotFoundException;
    String changeUserRole(String userId, String newRole);
    String changeAdminRole(String userId, String newRole);
}
