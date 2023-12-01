package com.joel.newsapp.dtos.users;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
@ToString
public class EmployeeInfoDTO {
    private String id;
    private Boolean enabled;
    private Double monthlySalary;
}
