package com.joel.newsapp.config;


import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                //.sessionManagement(session -> session
                       // .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        // URL de sesion vencida .invalidSessionUrl("/invalidSession"))
                //.authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/register","/login", "/error", "/publicimg").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/save", "/login").permitAll()
                        .requestMatchers("/error", "/roles").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/roles").permitAll()
                        .requestMatchers("/panel").hasAnyRole( Role.ADMIN.name(), Role.USER.name(), Role.REPORTER.name())
                        .requestMatchers("/reporter/**").hasRole(Role.REPORTER.toString())
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/news/edit/**").hasAnyRole(Role.ADMIN.name(), Role.REPORTER.name())
                        .requestMatchers("/news/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/comment/add/**").hasRole(Role.USER.name())
                        .requestMatchers(HttpMethod.GET, "/perfil/**").authenticated()
                        .anyRequest().denyAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        //.failureHandler(authenticationFailureHandler())
                        )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        //.logoutSuccessHandler(logoutSuccessHandler())
                        );


        return httpSecurity.build();
    }

}
