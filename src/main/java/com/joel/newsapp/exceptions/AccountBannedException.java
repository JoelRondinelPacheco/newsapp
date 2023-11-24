package com.joel.newsapp.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AccountBannedException extends UsernameNotFoundException {
    public AccountBannedException(String msg) {
        super(msg);
    }
}
