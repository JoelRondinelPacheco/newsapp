package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.EmployeePaginatedDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.UserState;

import java.util.List;

public interface IDashboardService {
    EmployeeDTO getEmployee(String id, Role role) throws NotFoundException;
    EmployeePaginatedDTO getAllEmployees(int pageNumber, int pageSize, Role role, UserState state);
}
