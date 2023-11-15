package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AdminRegisterUserDTO extends RegisterBaseDTO {
    private Role role;

    public AdminRegisterUserDTO(String name, String lastname, String email, String password, Role role) {
        super(name, lastname, email, password);
        this.role = role;
    }
}
