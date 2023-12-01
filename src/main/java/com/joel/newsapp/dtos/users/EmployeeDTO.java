package com.joel.newsapp.dtos.users;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class EmployeeDTO {
    private Double monthlySalary;
    private Boolean reporterEnabled;
    private String reporterId;
    private UserInfoDTO reporterInfo;
}
