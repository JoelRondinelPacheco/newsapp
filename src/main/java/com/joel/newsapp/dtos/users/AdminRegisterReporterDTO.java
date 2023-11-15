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
public class AdminRegisterReporterDTO extends AdminRegisterUserDTO {
    private Double monthlySalary;

    public AdminRegisterReporterDTO(String name, String lastname, String email, String password, Role role, Double monthlySalary) {
        super(name, lastname, email, password, role);
        this.monthlySalary = monthlySalary;
    }
}
