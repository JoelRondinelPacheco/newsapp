package com.joel.newsapp.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterBaseDTO {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
