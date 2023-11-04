package com.joel.newsapp.services;

import com.joel.newsapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;
/*
    @Autowired
    private JwtService jwtService;
*/
    @Autowired
    private PasswordEncoder passwordEncoder;
/*
    public AuthResponse login(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);

        User user = userRepository.findByUsername(authRequest.getEmail()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(User user) {
    }*/
}
