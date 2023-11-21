package com.joel.newsapp.services;

import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.repositories.IPasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordTokenService {
    @Autowired
    private IPasswordTokenRepository passwordToken;

    public PasswordToken saveToken(User user) {
        PasswordToken token = new PasswordToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        return this.passwordToken.save(token);
    }
}
