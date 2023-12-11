package com.joel.newsapp.dtos.users;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class EmployeeDTO {
    private Double monthlySalary;
    private Boolean employeeEnabled;
    private String employeeId;
    private UserInfoDTO userInfo;
}
