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
}
