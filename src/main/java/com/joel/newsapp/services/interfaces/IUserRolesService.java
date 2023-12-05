package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.exceptions.NotFoundException;

public interface IUserRolesService {
    String changeReporterRole(String userId, String newRole) throws NotFoundException;
    String changeUserRole(String userId, String newRole) throws NotFoundException;
    String changeAdminRole(String userId, String newRole) throws NotFoundException;
    String changeModeratorRole(String userId, String newRole) throws NotFoundException;
}
