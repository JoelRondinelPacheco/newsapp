package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
@ToString
public class UserInfoDTO extends UserProfileInfoDTO {
    private Role role;
    private Boolean enabled;
    private String id;
    private EmployeeInfoDTO employeeInfo;
/*
    public UserInfoDTO(String name, String lastname, String displayName, String email, String profilePicture, Role role, Boolean enabled, String id) {
        super(name, lastname, displayName, email, profilePicture);
        this.role = role;
        this.enabled = enabled;
        this.id = id;
    }*/
}
