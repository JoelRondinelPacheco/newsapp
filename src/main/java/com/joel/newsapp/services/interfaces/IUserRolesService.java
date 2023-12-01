package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;

public interface IUserRolesService {
    String changeReporterRole(String userId, Role newRole) throws NotFoundException;
    String changeUserRole(String userId, Role newRole) throws NotFoundException;
    String changeAdminRole(String userId, Role newRole) throws NotFoundException;
    String changeModeratorRole(String userId, Role newRole) throws NotFoundException;
}
