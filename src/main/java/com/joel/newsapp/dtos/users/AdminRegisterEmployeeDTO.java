package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AdminRegisterEmployeeDTO  {
    private Double monthlySalary;
    private AdminRegisterUserDTO user;


}
