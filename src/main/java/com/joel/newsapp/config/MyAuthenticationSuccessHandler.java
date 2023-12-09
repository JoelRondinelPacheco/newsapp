package com.joel.newsapp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        // Personaliza la lógica según tus necesidades
        String targetUrl = determineTargetUrl(request, response, authentication);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    public String determineTargetUrl(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) {
        String query = request.getQueryString() != null ? request.getQueryString() : "/";
        if (query.contains("redirect")) {
            return query.split("=")[1];
        } else {
            return "/";
        }
/*
        String query = request.getQueryString();
        if (query != null && query.contains("redirect")) {
            String redirect = query.split("=")[1];
            System.out.println("redirect");
            System.out.println(redirect);
            return redirect;
        } else {
            System.out.println("Redirect home");
            return "/";
        }*/
    }
}

