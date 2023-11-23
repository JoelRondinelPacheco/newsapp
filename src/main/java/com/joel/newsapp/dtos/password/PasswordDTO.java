package com.joel.newsapp.dtos.password;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PasswordDTO {
    private String token;
    private String password;
    private String confirmPassword;
}
