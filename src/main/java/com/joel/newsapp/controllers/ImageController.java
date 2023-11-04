package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.ImageService;
import com.joel.newsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;


    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> getImageByUserId (@PathVariable Long id) throws NotFoundException {
        User usuario = userService.getById(id);

        byte[] imagen= usuario.getImage().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen,headers, HttpStatus.OK);
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> getNewsImage (@PathVariable String id) throws NotFoundException {
        System.out.println(id);
        byte[] imagen= this.imageService.getById(id).getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen,headers, HttpStatus.OK);
    }
}
