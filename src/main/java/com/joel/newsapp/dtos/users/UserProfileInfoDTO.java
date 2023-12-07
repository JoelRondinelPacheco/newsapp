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
public class UserProfileInfoDTO {
    private String id;
    private String name;
    private String lastname;
    private String displayName;
    private String email;
    private String profilePictureId;

    public UserProfileInfoDTO(String id, String name, String lastname, String displayName, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.displayName = displayName;
        this.email = email;
    }
}
