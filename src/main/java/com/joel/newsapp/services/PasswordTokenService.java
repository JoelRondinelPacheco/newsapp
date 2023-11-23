package com.joel.newsapp.services;

import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IPasswordTokenRepository;
import com.joel.newsapp.services.interfaces.IPasswordTokenService;
import com.joel.newsapp.utils.PasswordTokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordTokenService implements IPasswordTokenService {
    @Autowired
    private IPasswordTokenRepository passwordToken;

    public PasswordToken saveToken(User user, PasswordTokenType type, boolean valid) {
        PasswordToken token = new PasswordToken();
        token.setUser(user);
        token.setType(type);
        token.setValid(valid);
        token.setToken(UUID.randomUUID().toString());
        return this.passwordToken.save(token);
    }

    @Override
    public PasswordToken getByToken(String token) throws NotFoundException {
        Optional<PasswordToken> tokenO = this.passwordToken.findByToken(token);
        if (tokenO.isPresent()) {
            return tokenO.get();
        }
        throw new NotFoundException("Token not found");
    }
}
