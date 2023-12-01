package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.Role;

public interface IUserRolesService {
    String changeRole(String userId, Role newRole) throws NotFoundException;
}
