package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IPasswordTokenService {
    PasswordToken saveToken(User user);
    PasswordToken getByToken(String token) throws NotFoundException;
}
