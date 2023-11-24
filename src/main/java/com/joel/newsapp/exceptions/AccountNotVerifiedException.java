package com.joel.newsapp.exceptions;

import javax.security.sasl.AuthenticationException;

public class AccountNotVerifiedException extends AuthenticationException {
    public AccountNotVerifiedException(String msg) {
        super(msg);
    }
}
