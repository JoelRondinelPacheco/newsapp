package com.joel.newsapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class Utils {
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
    public String previousURL(HttpServletRequest req) {
        String urlString = req.getHeader("Referer");
        try {
            URL url = new URL(urlString);
            String urlPath =  url.getPath();
            String query = url.getQuery() != null ? url.getQuery() : "";
            String urlFinal = urlPath + "?" + query;
            System.out.println("URL FINAL: " + urlFinal);
            return urlFinal;
        } catch (MalformedURLException e) {
            return "/";
        }
    }

}
