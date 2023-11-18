package com.joel.newsapp.dtos.users;

import com.joel.newsapp.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AdminRegisterEmployeeDTO extends AdminRegisterUserDTO {
    private Double monthlySalary;

    public AdminRegisterEmployeeDTO(String name, String lastname, String email, Role role) {
        super(name, lastname, email, role);
    }
    public AdminRegisterEmployeeDTO(String name, String lastname, String email, Role role, Double monthlySalary) {
        super(name, lastname, email, role);
        this.monthlySalary = monthlySalary;
    }

}
