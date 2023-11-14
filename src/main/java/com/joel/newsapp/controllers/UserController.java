package com.joel.newsapp.controllers;

import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.ImageService;
import com.joel.newsapp.services.NewsService;
import com.joel.newsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private NewsService newsService;

    @GetMapping("/public-img")
    public ResponseEntity<byte[]> userImage () throws NotFoundException {
        byte[] image = this.imageService.getById("20e1392a-4bc6-433a-a114-361c534c02b5").getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image,headers, HttpStatus.OK);
    }

    @GetMapping("/panel")
    public String panel(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOptional = this.userRepository.findUser(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
                    return "user_panel.html";
        }

        model.put("error", "User not found");

        return "user_panel.html";
    }
    /*
      @Query("SELECT u FROM User u WHERE u.country = :userCountry")
    List<User> findUsersByCountry(@Param("userCountry") String userCountry);

    @Query("SELECT u FROM User u WHERE u.country = :userCountry AND u.city = :userCity")
    List<User> findUsersByCountryCity(@Param("userCountry") String userCountry, @Param("userCity") String userCity);

    @Query("SELECT u FROM User u WHERE u.country = :userCountry AND u.city = :userCity AND u.province = :userProvince")
    List<User> findUsersByCountryCityProvince(@Param("userCountry") String userCountry, @Param("userCity") String userCity, @Param("userProvince") String userProvince);

    @Query("SELECT u FROM User u WHERE u.postal_code = :userPostal")
    List<User> findUsersByPostalCode(@Param("userPostal") String userPostal);
     */
}
