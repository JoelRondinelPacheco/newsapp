package com.joel.newsapp.services;

import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.exceptions.ValidateAccountException;
import com.joel.newsapp.repositories.IPasswordTokenRepository;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.interfaces.IPasswordTokenService;
import com.joel.newsapp.utils.PasswordTokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.Validate;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordTokenService implements IPasswordTokenService {
    @Autowired
    private IPasswordTokenRepository passwordToken;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPasswordTokenRepository passwordTokenRepository;

    @Override
    public String validateAccount(String token) throws ValidateAccountException {
        try {
            PasswordToken passwordToken = this.getByToken(token);
            if (passwordToken.isValid() && passwordToken.getType() == PasswordTokenType.VALIDATE) {
                User user = passwordToken.getUser();

                if (!user.getEnabled()) {
                    throw new ValidateAccountException("User banned");
                }

                user.setVerified(true);
                this.userRepository.save(user);
                passwordToken.setValid(false);
                this.passwordTokenRepository.save(passwordToken);
                return "User validated";
            } else {
                throw new ValidateAccountException("Token invalid");
            }
        } catch (NotFoundException e) {
            throw new ValidateAccountException(e.getMessage());
        }
    }

    @Override
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
