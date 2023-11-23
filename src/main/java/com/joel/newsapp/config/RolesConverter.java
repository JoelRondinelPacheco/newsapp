package com.joel.newsapp.config;

import com.joel.newsapp.utils.Role;
import org.springframework.core.convert.converter.Converter;

public class RolesConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.valueOf(source.toUpperCase());
    }
}
