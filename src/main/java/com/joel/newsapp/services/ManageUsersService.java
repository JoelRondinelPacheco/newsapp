package com.joel.newsapp.services;

import com.joel.newsapp.dtos.mail.SendMailDTO;
import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.AdminRegisterUserDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Admin;
import com.joel.newsapp.entities.Moderator;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IAdminRepository;
import com.joel.newsapp.repositories.IModeratorRepository;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.repositories.IUserRepository;
import com.joel.newsapp.services.interfaces.IAdminManageUsers;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.services.interfaces.IUserService;
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
    private MailService mailService;
    @Autowired
    private PasswordTokenService tokenService;

    @Autowired
    private IUserService userService;
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
                        .enabled(false)
                        .build();
                this.reporterRepository.save(reporter);
                break;
            case MODERATOR:
                Moderator moderator = Moderator.builder()
                        .monthlySalary(employee.getMonthlySalary())
                        .user(user)
                        .enabled(false)
                        .build();
                this.moderatorRepository.save(moderator);
                break;
            case ADMIN:
                Admin admin = Admin.builder()
                        .monthlySalary(employee.getMonthlySalary())
                        .user(user)
                        .enabled(false)
                        .build();
                this.adminRepository.save(admin);
                break;
        }
        mail.setMessage("Cree su contraseña en el siguiente enlace, con token: "));
        String res = this.mailService.sendMail(mail);
        return res;

    }

    @Override
    public String changeReporterRole(String userId, String newRole) throws NotFoundException {
        return null;
    }

    @Override
    public String changeUserRole(String userId, String newRole) {
        return null;
    }

    @Override
    public String changeAdminRole(String userId, String newRole) {
        return null;
    }

    @Override
    public String createUser(AdminRegisterUserDTO userDTO) {
        User user = this.adminRegisterUser(userDTO);
        SendMailDTO mail = new SendMailDTO();
        mail.setTo(user.getEmail());
        mail.setSubject("Se registro tu cuenta");
        mail.setMessage("Cree su contraseña en el siguiente enlace, con token: " + user.getPasswordToken());
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
                .active(false)
                .build();
        user.setImage(this.imageService.defaultImage());
        User userSaved =  this.userRepository.save(user);
        this.tokenService.saveToken(userSaved);
        this.
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
