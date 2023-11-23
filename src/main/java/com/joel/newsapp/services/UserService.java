package com.joel.newsapp.services;

import com.joel.newsapp.dtos.mail.SendMailDTO;
import com.joel.newsapp.dtos.users.*;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.PasswordToken;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.PasswordTokenType;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Utils utils;
    @Autowired
    private JwtTokenService jwtService;
    @Autowired
    private PasswordTokenService tokenService;
    @Autowired
    private MailService mailService;


    @Override
    public UserInfoDTO registerDTO(RegisterUserDTO userDTO){
        User userSaved = this.register(userDTO);
        return this.createUserInfoDTO(userSaved);
    }
    @Override
    public User register(RegisterUserDTO userDTO) {
        User user = new User();
        String pass = this.utils.encryptPassword(userDTO.getPassword());
        user.setPassword(pass);
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
            Image img = this.imageService.defaultImage();
            user.setImage(img);
        } else {
            Image img = this.imageService.save(userDTO.getProfilePicture());
            user.setImage(img);
        }

        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setEnabled(true);
        user.setActive(false);
        User userSaved = this.userRepository.save(user);
        PasswordToken token = this.tokenService.saveToken(userSaved, PasswordTokenType.CONFIRM, true);

        SendMailDTO mail = new SendMailDTO();
        mail.setTo(userSaved.getEmail());
        mail.setSubject("Cuenta creada");
        mail.setMessage(token.getToken());
        this.mailService.sendMail(mail);

        return userSaved;
    }


    @Override
    public UserInfoDTO getById(String id) throws NotFoundException {
       User user = this.findById(id);
       return this.createUserInfoDTO(user);
    }

    @Override
    public UserInfoDTO edit(EditUserDTO userDTO) throws NotFoundException {
        User user = this.findById(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setDisplayName(userDTO.getDisplayName());
        if (!userDTO.getProfilePicture().isEmpty()) {
            this.imageService.update(userDTO.getProfilePicture(), user.getImage().getId());
        }
        User userUpdated = this.userRepository.save(user);
        return this.createUserInfoDTO(userUpdated);
    }

    @Override
    public String deleteById(String id) throws NotFoundException {
        boolean exists = this.userRepository.existsById(id);
        if (exists) {
            User user = this.userRepository.findById(id).get();
            user.setEnabled(false);
            this.userRepository.save(user);
            return "User deleted";
        } else {
            throw new NotFoundException("User not found");
        }
    }


    @Override
    public List<UserInfoDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return this.listUserInfoDTO(users);
    }

    @Override
    public List<UserInfoDTO> getUsersByEnabledAndRole(Boolean enabled, Role role) {
       List<User> users = this.userRepository.findByRoleAndEnabled(role, enabled);
        return this.listUserInfoDTO(users);
    }

    public UserInfoDTO findByEmail(String username) throws NotFoundException {
        Optional<User> userOptional = this.userRepository.findByEmail(username);
        if(userOptional.isPresent()) {
            return this.createUserInfoDTO(userOptional.get());
        }
        throw new NotFoundException("User not found");

    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException  {
        Optional<User> userOptional = this.userRepository.findByEmail(email);
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


    private User findById(String id) throws NotFoundException {
        Optional<User> userO = this.userRepository.findById(id);
        if(userO.isPresent()) {
            return userO.get();
        }
        throw new NotFoundException("User not found");
    }

    private UserInfoDTO createUserInfoDTO(User user) {
        return new UserInfoDTO(user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail(), user.getImage().getId(), user.getRole(), user.getEnabled(), user.getId());
    }
    private List<UserInfoDTO> listUserInfoDTO(List<User> users) {
        List<UserInfoDTO> usersDTO = new ArrayList<>();
        for(User u : users) {
            usersDTO.add(this.createUserInfoDTO(u));
        }
        return usersDTO;
    }

    private UserProfileInfoDTO createUserProfileInfo(User user) {
        return new UserProfileInfoDTO(user.getName(), user.getLastname(), user.getDisplayName(), user.getEmail(), user.getImage().getId());
    }
}
