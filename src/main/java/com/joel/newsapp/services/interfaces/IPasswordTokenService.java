package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.utils.PasswordTokenType;

public interface IPasswordTokenService {
    PasswordToken saveToken(User user, PasswordTokenType type, boolean valid);
    PasswordToken getByToken(String token) throws NotFoundException;
}