package com.joel.newsapp.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class RegisterUserDTO extends RegisterBaseDTO {

    private MultipartFile profilePicture;

    public RegisterUserDTO(String name, String lastname, String email, String password, MultipartFile profilePicture) {
        super(name, lastname, email, password);
        this.profilePicture = profilePicture;
    }

}
