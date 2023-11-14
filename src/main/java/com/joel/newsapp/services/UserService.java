package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.*;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ImageService imageService;
    private static final String IMAGE_DIRECTORY = "static/img/user.jpeg";


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
        user.setLastname(userDTO.getLastname());

        if (userDTO.getName().isEmpty()) {
            user.setDisplayName(userDTO.getLastname());
        } else if(userDTO.getLastname().isEmpty()) {
            user.setDisplayName(userDTO.getName());
        } else if (!userDTO.getLastname().isEmpty() && !userDTO.getName().isEmpty()){
            user.setDisplayName(userDTO.getName() + "." + userDTO.getLastname());
        } else {
            user.setDisplayName("");
        }

        if(userDTO.getProfilePicture().isEmpty()) {
            Image img = new Image();
            ClassPathResource imagePath = new ClassPathResource(IMAGE_DIRECTORY);
            img.setName("default");
            try {
                img.setContent(Files.readAllBytes(Path.of(imagePath.getURI())));
            } catch (IOException e) {
                System.out.println("ERROR: " +e.getMessage());
            }
            img.setMime("image/jpeg");
            Image imgSaved = this.imageService.saveDb(img);
            user.setImage(imgSaved);
        } else {
            Image img = this.imageService.save(userDTO.getProfilePicture());
            user.setImage(img);
        }

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

    public UserInfoDTO findByEmail(String username) throws NotFoundException {
        Optional<User> userOptional = this.userRepository.findUser(username);
        if(userOptional.isPresent()) {
            return this.createUserInfoDTO(userOptional.get());
        }
        throw new NotFoundException("User not found");

    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException  {
        Optional<User> userOptional = this.userRepository.findUser(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UsernameNotFoundException ("User not found");
    }

    @Override
    public UserProfileInfoDTO userProfileInfo(String email) throws NotFoundException {
        User user = this.findUserByEmail(email);
        return this.createUserProfileInfo(user);
    }



    private UserInfoDTO createUserInfoDTO(User user) {
        if (user.getImage() != null) {
            return new UserInfoDTO(user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail(), user.getImage().getId(), user.getRole(), user.getEnabled());
        } else {
            return new UserInfoDTO(user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail(), "publicimg", user.getRole(), user.getEnabled());
        }
    }

    private UserProfileInfoDTO createUserProfileInfo(User user) {
        UserProfileInfoDTO info = new UserProfileInfoDTO(user.getName(), user.getLastname(), user.getDisplayName() == null ? "" : user.getDisplayName(), user.getEmail());
        if (user.getImage() != null) {
            info.setProfilePictureId(user.getImage().getId());
        } else {
            info.setProfilePictureId("publicimg");
        }
        return info;
    }
}
