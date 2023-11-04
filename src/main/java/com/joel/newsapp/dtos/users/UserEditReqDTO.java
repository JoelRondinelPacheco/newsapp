package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEditReqDTO extends UserPostReqDTO {
    private Role rol;
    private Boolean active;
}
