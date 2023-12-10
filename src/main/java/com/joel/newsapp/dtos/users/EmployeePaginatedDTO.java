package com.joel.newsapp.dtos.users;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class EmployeePaginatedDTO {
    private List<EmployeeDTO> employees;
    private int totalPages;
    private Long totalElements;
}
