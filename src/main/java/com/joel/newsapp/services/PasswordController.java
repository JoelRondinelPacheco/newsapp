package com.joel.newsapp.services;

import com.joel.newsapp.dtos.password.PasswordDTO;
import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IPasswordTokenRepository;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.interfaces.IPasswordController;
import com.joel.newsapp.services.interfaces.IPasswordTokenService;
import com.joel.newsapp.utils.PasswordTokenType;
import com.joel.newsapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordController implements IPasswordController {
    @Autowired
    private IPasswordTokenService tokenService;
    @Autowired
    private IPasswordTokenRepository tokenRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private Utils utils;

    @Override
    public String setPassword(PasswordDTO password) throws NotFoundException {
        //TODO CHECK TWO PASSWORDS
        PasswordToken token = this.tokenService.getByToken(password.getToken());
        User user = token.getUser();
        if (!user.getVerified() && user.getEnabled() && token.isValid() && token.getType().name().equals(PasswordTokenType.SET.name())) {
            String pass = this.utils.encryptPassword(password.getPassword());
            user.setPassword(pass);
            user.setVerified(true);
            token.setValid(false);
            this.tokenRepository.save(token);
            this.userRepository.save(user);
            return "Password setted";
        }
        //TODO CHANGE EXCEPTION
        throw new NotFoundException("user not found");
    }

    @Override
    public String resetPassword(PasswordDTO password) throws NotFoundException {
        PasswordToken token = this.tokenService.getByToken(password.getToken());
        User user = token.getUser();
        if (user.getVerified() && user.getEnabled() && token.isValid() && token.getType().name().equals(PasswordTokenType.RESET.name())) {
            String pass = this.utils.encryptPassword(password.getPassword());
            user.setPassword(pass);
            token.setValid(false);
            this.tokenRepository.save(token);
            this.userRepository.save(user);
            return "Password re-setted";
        }
        //TODO CHANGE EXCEPTION
        throw new NotFoundException("user not found");
    }
}
