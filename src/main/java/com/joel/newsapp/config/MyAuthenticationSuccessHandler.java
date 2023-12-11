package com.joel.newsapp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    public String determineTargetUrl(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) {
        String urlString = request.getHeader("Referer");

        try {
            URL url = new URL(urlString);
            String urlPath =  url.getPath();
            System.out.println("SOUTS URL");
            System.out.println("url path: "+ urlPath);
            System.out.println("url query: " + url.getQuery());
            String urlFinal = urlPath + "?" + url.getQuery();
            System.out.println("URL FINAL: " + urlFinal);
            if (urlPath.equals("/login")) {
                return "/";
            }
            return urlFinal;
        } catch (MalformedURLException e) {
            return "/";
        }
    }
}

