package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEditReqDTO extends RegisterUserDTO {
    private Role rol;
    private Boolean active;
}
