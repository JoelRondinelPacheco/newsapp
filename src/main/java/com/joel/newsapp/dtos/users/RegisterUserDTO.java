package com.joel.newsapp.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterUserDTO {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private MultipartFile profilePicture;

}
