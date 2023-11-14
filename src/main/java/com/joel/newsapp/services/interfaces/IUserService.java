package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.EditUserDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;

import java.util.List;

public interface IUserService extends ICrudService<UserInfoDTO, RegisterUserDTO, EditUserDTO, String> {
    List<UserInfoDTO> getAllUsers();

}
