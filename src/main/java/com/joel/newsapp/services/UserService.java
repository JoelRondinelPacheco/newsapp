package com.joel.newsapp.services;

import com.joel.newsapp.dtos.users.UserEditReqDTO;
import com.joel.newsapp.dtos.users.UserLoginDTO;
import com.joel.newsapp.dtos.users.UserPostReqDTO;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.services.interfaces.ICrudService;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ICrudService<User, UserPostReqDTO, UserEditReqDTO, Long> {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public User save(UserPostReqDTO userDTO){
        String pass = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        Image image = this.imageService.save(userDTO.getImage());
        User user = new User(userDTO.getName(), userDTO.getEmail(), pass, image);
        return this.userRepository.save(user);
    }


    @Override
    public User getById(Long id) throws NotFoundException {
        Optional<User> userEOptional = this.userRepository.findById(id);
        if (userEOptional.isPresent()) {
            return userEOptional.get();
        }
        throw new NotFoundException("Usuario no encontrado");
    }

    public UserLoginDTO infoDTOById(Long id) throws NotFoundException {
        Optional<UserLoginDTO> userO = this.userRepository.infoDTOById(id);
        if (userO.isPresent()) {
            return userO.get();
        }
        throw new NotFoundException("Usuario no encontrado");
    }
    public User findByEmail(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findUser(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UsernameNotFoundException("User not found");

    };

    public UserLoginDTO findByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLoginDTO> userOptional = this.userRepository.findByUsername(username);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UsernameNotFoundException("User not found");

    };

    @Override
    public User edit(UserEditReqDTO userEditReqDTO) throws NotFoundException {
        return null;
    }

    public String updateSalaryAndEnabled(Integer salary, boolean active, Long id){
       int filas =this.userRepository.updateSalaryAndEnabled(salary, active, id);
        if (filas != 0) {
            System.out.println("encontro");
            return "Updated";
        }
        System.out.println("no encontro");
        return "Updated failed";

    }

    public List<User> getAllUsers() {
        return this.userRepository.finAllUsers();
    }

    public List<User> getAllReporters() {
        List<User> users =  this.userRepository.finAllReporters();
       /* List<Reporter> reporters = new ArrayList<>();

        for (UserE user : users) {
            Reporter reporter = (Reporter) user;
            reporters.add(reporter);
        }*/
    return users;
    }

    @Override
    public String deleteById(Long aLong) {
        return null;
    }

    public User roles(Role rol, Long id) throws NotFoundException {
        User user = this.getById(id);
        user.setRole(rol);
        return this.userRepository.save(user);
    }
}
