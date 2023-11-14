package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.EditUserDTO;
import com.joel.newsapp.dtos.users.UserEditReqDTO;
import com.joel.newsapp.dtos.users.RegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public UserInfoDTO save(RegisterUserDTO userDTO){
        User user = new User();
        String pass = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        user.setPassword(pass);
        if (!userDTO.getProfilePicture().isEmpty()) {
            Image image = this.imageService.save(userDTO.getProfilePicture());
            user.setImage(image);
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.USER);
        User userSaved = this.userRepository.save(user);
        return this.userRepository.getUserInfoDTO(userSaved.getId()).get();
    }


    @Override
    public UserInfoDTO getById(String id) throws NotFoundException {
        Optional<UserInfoDTO> userO = this.userRepository.getUserInfoDTO(id);
        if (userO.isPresent()) {
            return userO.get();
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public UserInfoDTO edit(EditUserDTO userDTO) throws NotFoundException {
        Optional<User> userO = this.userRepository.findById(userDTO.getId());
        if (userO.isPresent()) {
            User user = userO.get();
            user.setName(userDTO.getName());
            user.setLastname(userDTO.getLastname());
            if (!userDTO.getProfilePicture().isEmpty()) {
                if (user.getImage() != null) {
                    Image img = user.getImage();
                    this.imageService.update(userDTO.getProfilePicture(), user.getId());
                } else {
                    Image img = this.imageService.save(userDTO.getProfilePicture());
                    user.setImage(img);
                }
            }
            this.userRepository.save(user);
            return this.userRepository.getUserInfoDTO(user.getId()).get();
        }
        throw new NotFoundException("User not found");
    }

    @Override
    public String deleteById(String id) throws NotFoundException {
        boolean exists = this.userRepository.existsById(id);
        if (exists) {
            this.userRepository.deleteById(id);
            return "User deleted";
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public List<UserInfoDTO> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    public User findByEmail(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findUser(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UsernameNotFoundException("User not found");

    };



/*
    public User roles(Role rol, String id) throws NotFoundException {
        User user = this.getById(id);
        user.setRole(rol);
        return this.userRepository.save(user);
    }*/

    private UserInfoDTO createUserInfoDTO(User user) {
        return new UserInfoDTO(user.getName(), user.getLastname(), user.getEmail(), user.getRole(), user.getEnabled(), user.getImage().getId());

    }
}
