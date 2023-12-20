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

    @Autowired private AuthenticationProvider authenticationProvider;
    @Autowired  private MyAuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .cors(c -> c.disable())
                //.sessionManagement(session -> session
                       // .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        // URL de sesion vencida .invalidSessionUrl("/invalidSession"))
                //.authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/*", "/scripts/**", "/img/**", "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/**", "/register","/login", "/error", "/register/**", "/account/**").permitAll()
                        .requestMatchers("/publicimg", "/image/**", "/news/view/**", "/search/**").permitAll()
                        .requestMatchers("/user/panel", "/user/edit/**").authenticated()
                        .requestMatchers("/error", "/roles").permitAll()
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/dashboard", "/dashboard/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/mail/**", "/role/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/comment/add/**").authenticated()
                        .requestMatchers("/comment/delete/**").hasAnyRole(Role.ADMIN.name(), Role.MODERATOR.name())
                        .requestMatchers("/comment-reaction/**").authenticated()
                        .requestMatchers("/panel").authenticated()
                        .requestMatchers("/reporter/**").hasRole(Role.REPORTER.toString())
                        .requestMatchers("/news/save", "/news/edit/**").hasRole(Role.REPORTER.toString())
                        .requestMatchers("/news/edit/**").hasAnyRole(Role.ADMIN.name(), Role.REPORTER.name())
                        .requestMatchers("/news/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/comment/add/**").hasRole(Role.USER.name())
                        .requestMatchers(HttpMethod.GET, "/perfil/**").authenticated()
                        .requestMatchers("/utils/**").permitAll()
                        .anyRequest().denyAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(authenticationSuccessHandler)
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
