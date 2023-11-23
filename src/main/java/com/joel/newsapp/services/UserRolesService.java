package com.joel.newsapp.services;

import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.IUserRolesService;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService implements IUserRolesService {
    @Override
    public String changeReporterRole(String userId, String newRole) throws NotFoundException {
        return null;
    }

    @Override
    public String changeUserRole(String userId, String newRole) throws NotFoundException {
        return null;
    }

    @Override
    public String changeAdminRole(String userId, String newRole) throws NotFoundException  {
        return null;
    }

    @Override
    public String changeModeratorRole(String userId, String newRole) throws NotFoundException {
        return null;
    }
}
