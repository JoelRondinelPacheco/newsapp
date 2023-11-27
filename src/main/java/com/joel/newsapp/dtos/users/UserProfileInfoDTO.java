package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserProfileInfoDTO {
    private String name;
    private String lastname;
    private String displayName;
    private String email;
    private String profilePictureId;

    public UserProfileInfoDTO(String name, String lastname, String displayName, String email) {
        this.name = name;
        this.lastname = lastname;
        this.displayName = displayName;
        this.email = email;
    }
}
