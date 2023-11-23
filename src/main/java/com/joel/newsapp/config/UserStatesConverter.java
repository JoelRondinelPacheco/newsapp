package com.joel.newsapp.config;

import com.joel.newsapp.utils.UserState;
import org.springframework.core.convert.converter.Converter;

public class UserStatesConverter implements Converter<String, UserState> {
    @Override
    public UserState convert(String source) {
        return UserState.valueOf(source.toUpperCase());
    }
}
