package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.exceptions.NotFoundException;

public interface IAdminService {
    void changeReporterRole(String userId, String newRole) throws NotFoundException;

    void changeUserRole(String userId, String newRole);

    void changeAdminRole(String userId, String newRole);
}
