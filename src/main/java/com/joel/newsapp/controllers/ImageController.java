package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Image;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;


    @GetMapping("/profile/{id}")
    public ResponseEntity<byte[]> getImageByUserId (@PathVariable String id) throws NotFoundException {
        //TODO REFACTOR
        UserInfoDTO user = userService.getById(id);
        Image img = this.imageService.getById(user.getProfilePictureId());
        byte[] image = img.getContent();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image,headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getNewsImage (@PathVariable String id) throws NotFoundException {
        System.out.println(id);
        byte[] image = this.imageService.getById(id).getContent();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(image,headers, HttpStatus.OK);
    }
}
