package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IAdminManageUsers {
    void createEmployee(AdminRegisterEmployeeDTO employee);
    String changeReporterRole(String userId, String newRole) throws NotFoundException;
    String changeUserRole(String userId, String newRole);
    String changeAdminRole(String userId, String newRole);
    UserInfoDTO createUser(RegisterUserDTO userDTO);

}
