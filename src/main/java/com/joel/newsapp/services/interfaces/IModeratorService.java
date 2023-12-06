package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.entities.Moderator;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IModeratorService {
    Moderator findByUserId(String userId) throws NotFoundException;
}
