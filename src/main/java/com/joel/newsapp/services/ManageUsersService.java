package com.joel.newsapp.services;

import com.joel.newsapp.dtos.mail.SendMailDTO;
import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.AdminRegisterUserDTO;
import com.joel.newsapp.entities.*;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.*;
import com.joel.newsapp.services.interfaces.IAdminManageUsers;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.PasswordTokenType;
import com.joel.newsapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageUsersService implements IAdminManageUsers {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IReporterService reporterService;
    @Autowired
    private JwtTokenService jwtService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private IReporterRepository reporterRepository;
    @Autowired
    private IModeratorRepository moderatorRepository;
    @Autowired
    private IAdminRepository adminRepository;
    @Autowired
    private EmailService mailService;
    @Autowired
    private PasswordTokenService tokenService;
    @Autowired
    private IUserService userService;
    @Autowired
    private Utils utils;
    @Autowired
    private IPasswordTokenRepository tokenRepository;

    @Override
    public String createEmployee(AdminRegisterEmployeeDTO employee) {
        SendMailDTO mail = new SendMailDTO();
        mail.setTo(employee.getUser().getEmail());
        mail.setSubject("Se registro tu cuenta");
        User user = this.adminRegisterUser(employee.getUser());
        switch (employee.getUser().getRole()) {
            case REPORTER:
                Reporter reporter = Reporter.builder()
                        .monthlySalary(employee.getMonthlySalary())
                        .user(user)
                        .enabled(true)
                        .build();
                this.reporterRepository.save(reporter);
                break;
            case MODERATOR:
                Moderator moderator = Moderator.builder()
                        .monthlySalary(employee.getMonthlySalary())
                        .user(user)
                        .enabled(true)
                        .build();
                this.moderatorRepository.save(moderator);
                break;
            case ADMIN:
                Admin admin = Admin.builder()
                        .monthlySalary(employee.getMonthlySalary())
                        .user(user)
                        .enabled(true)
                        .build();
                this.adminRepository.save(admin);
                break;
        }
        PasswordToken token = this.tokenService.saveToken(user, PasswordTokenType.SET, true);
        mail.setMessage("Cree su contraseña en el siguiente enlace, con token: "+ token.getToken());
        String res = this.mailService.sendMail(mail);
        return res;

    }

    @Override
    public String createUser(AdminRegisterUserDTO userDTO) {
        User user = this.adminRegisterUser(userDTO);
        PasswordToken token = this.tokenService.saveToken(user, PasswordTokenType.SET, true);
        SendMailDTO mail = new SendMailDTO();
        mail.setTo(user.getEmail());
        mail.setSubject("Se registro tu cuenta");
        mail.setMessage("Cree su contraseña en el siguiente enlace, con token: "+ token.getToken());
        return this.mailService.sendMail(mail);
    }


    @Override
    public User adminRegisterUser(AdminRegisterUserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .lastname(userDTO.getLastname())
                .displayName(userDTO.getName() + "." + userDTO.getLastname())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .enabled(true)
                .verified(false)
                .build();
        user.setImage(this.imageService.defaultImage());
        return this.userRepository.save(user);
    }

    @Override
    public String adminEnabledState(String id, Boolean state) throws NotFoundException {
        boolean exists = this.userRepository.existsById(id);
        if (exists) {
            User user = this.userRepository.findById(id).get();
            user.setEnabled(state);
            this.userRepository.save(user);
            return "User deleted";
        }
        throw new NotFoundException("User not found");
    }


}
