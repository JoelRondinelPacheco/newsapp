package com.joel.newsapp.services;

import com.joel.newsapp.services.interfaces.IModeratorService;
import org.springframework.stereotype.Service;

@Service
public class ModeratorService implements IModeratorService {
    @Override
    public void changeModeratorRole(String userId, String newRole) {

    }
}
