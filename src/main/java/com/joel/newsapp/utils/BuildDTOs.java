package com.joel.newsapp.utils;

import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.Employee;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.dtos.users.UserProfileInfoDTO;
import com.joel.newsapp.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildDTOs {

    public UserInfoDTO createUserInfoDTO(User user) {
        UserInfoDTO userInfo = UserInfoDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .id(user.getId())
                .build();
        if (user.getImage() == null) {
            userInfo.setProfilePictureId("user_img");
        } else {
            userInfo.setProfilePictureId(user.getImage().getId());
        }
        return userInfo;
    }
    public List<UserInfoDTO> listUserInfoDTO(List<User> users) {
        List<UserInfoDTO> usersDTO = new ArrayList<>();
        for(User u : users) {
            usersDTO.add(this.createUserInfoDTO(u));
        }
        return usersDTO;
    }

    public UserProfileInfoDTO createUserProfileInfo(User user) {
        UserProfileInfoDTO userDTO = new UserProfileInfoDTO(user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail());
        if (user.getImage() == null) {
            userDTO.setProfilePictureId("user_image");
        } else {
            userDTO.setProfilePictureId(user.getImage().getId());
        }
        return userDTO;
    }

    public EmployeeDTO createEmployeeInfo(Employee employee) {
        User user = employee.getUser();
        UserInfoDTO userInfo = UserInfoDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .id(user.getId())
                .build();
        if (user.getImage() == null) {
            userInfo.setProfilePictureId("user_img");
        } else {
            userInfo.setProfilePictureId(user.getImage().getId());
        }
        return new EmployeeDTO(employee.getMonthlySalary(), employee.getEnabled(), employee.getId(), userInfo);
    }
}
