package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.IAdminManageUsers;

public class AdminManageUsers implements IAdminManageUsers {

    @Override
    public void createEmployee(AdminRegisterEmployeeDTO employee) {

    }

    @Override
    public String changeReporterRole(String userId, String newRole) throws NotFoundException {
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


}
