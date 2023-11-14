package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserInfoDTO {
    private String name;
    private String lastname;
    private String email;
    private Role role;
    private Boolean enabled;
    private String profilePictureId;
}
