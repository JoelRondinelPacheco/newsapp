package com.joel.newsapp.exceptions;

import javax.security.sasl.AuthenticationException;

public class AccountBannedException extends AuthenticationException {
    public AccountBannedException(String msg) {
        super(msg);
    }
}
