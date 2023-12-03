package com.joel.newsapp.controllers;

import com.joel.newsapp.utils.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/utils")
public class UtilsController {

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getAllRoles() {
        Role[] rolesEnum = Role.values();
        List<String> roles = new ArrayList<>();
        for (Role r : rolesEnum) {
            roles.add(r.toString().substring(0, 1) + r.toString().substring(1).toLowerCase());
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
