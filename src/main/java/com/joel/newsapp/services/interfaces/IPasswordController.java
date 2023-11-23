package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.password.PasswordDTO;
import com.joel.newsapp.exceptions.NotFoundException;

public interface IPasswordController {
    String setPassword(PasswordDTO password) throws NotFoundException;
    String resetPassword(PasswordDTO password) throws  NotFoundException;
}
