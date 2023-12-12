package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.*;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    UserInfoDTO registerDTO(RegisterUserDTO userDTO);
    User register(RegisterUserDTO userDTO);
    UserInfoDTO getById(String id) throws NotFoundException;
    UserInfoDTO edit(EditUserDTO userDTO) throws NotFoundException;
    String deleteById(String id) throws NotFoundException;
    List<UserInfoDTO> getAllUsers();
    UsersPaginatedDTO getUsersByEnabledAndRole(int pageNumber, int pageSize, UserState state, Role role);
    UserInfoDTO findByEmail(String username) throws NotFoundException;
    User findUserByEmail(String email) throws UsernameNotFoundException;
    UserProfileInfoDTO userProfileInfo(String email) throws NotFoundException;
    Boolean checkUserEmail(String email);
    User findById(String id) throws NotFoundException;
    Boolean existsByEmail(String email);


}
