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
    User adminRegisterUser(AdminRegisterUserDTO userDTO);
    String adminEnabledState(String id, Boolean state) throws NotFoundException;

}
