package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;

import java.util.List;

public interface IDashboardService {
    EmployeeDTO getEmployee(String id, Role role) throws NotFoundException;
    List<EmployeeDTO> getAllEmployees(Role role);
}
