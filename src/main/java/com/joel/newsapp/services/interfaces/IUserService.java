package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.EditUserDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UserProfileInfoDTO;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService extends ICrudService<UserInfoDTO, RegisterUserDTO, EditUserDTO, String> {
    List<UserInfoDTO> getAllUsers();
    UserInfoDTO findByEmail(String username) throws NotFoundException;
    User findUserByEmail(String email) throws UsernameNotFoundException;
    UserProfileInfoDTO userProfileInfo(String email) throws NotFoundException;


}
