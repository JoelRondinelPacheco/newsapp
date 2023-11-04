package com.joel.newsapp.config;

import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.CustomUserDetailsService;
import com.joel.newsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeansInjector {

    // Crear password enconder
    // Crear un authentication providers
    // Crear authentication manager
    // Crear security filter chain

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //Se creo el providerManager que implementa la interfaz AuthenticationManager
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        // Crear el DAO provider
        // Especificar el servicio que provee el detalle de usuarios
        // Especificar el password encoder
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }*/
}
