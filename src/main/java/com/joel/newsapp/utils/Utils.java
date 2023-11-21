package com.joel.newsapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
