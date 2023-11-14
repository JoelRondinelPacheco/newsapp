package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserInfoDTO extends UserProfileInfoDTO {
    private Role role;
    private Boolean enabled;

    public UserInfoDTO(String name, String lastname, String displayName, String email, String profilePictureId, Role role, Boolean enabled) {
        super(name, lastname, displayName, email, profilePictureId);
        this.role = role;
        this.enabled = enabled;
    }
}
