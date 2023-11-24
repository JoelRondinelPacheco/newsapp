package com.joel.newsapp.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.security.sasl.AuthenticationException;

public class AccountNotVerifiedException extends UsernameNotFoundException {
    public AccountNotVerifiedException(String msg) {
        super(msg);
    }
}
