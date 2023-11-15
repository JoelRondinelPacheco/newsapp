package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.*;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService extends ICrudService<UserInfoDTO, RegisterUserDTO, EditUserDTO, String> {

    List<UserInfoDTO> getAllUsers();
    List<UserInfoDTO> getUsersByEnabledAndRole(Boolean enabled, Role role);
    UserInfoDTO findByEmail(String username) throws NotFoundException;
    User findUserByEmail(String email) throws UsernameNotFoundException;
    UserProfileInfoDTO userProfileInfo(String email) throws NotFoundException;
    UserInfoDTO adminRegister(AdminRegisterUserDTO userDTO);
    String adminActiveState(String id, Boolean state) throws NotFoundException;
}
