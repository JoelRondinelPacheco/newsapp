package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.UserLoginDTO;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//Otro forma de implementar el user details service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    //TODO REFACTOR
    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        /*User user = this.userRepository.findByEmail(email).get();
        return user;*/
        User user = this.userService.findUserByEmail(email);

        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();


    }
}


