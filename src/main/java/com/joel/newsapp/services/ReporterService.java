package com.joel.newsapp.services;

import com.joel.newsapp.dtos.reporter.EditReporterDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.AdminRegisterEmployeeDTO;
import com.joel.newsapp.dtos.users.EmployeeDTO;
import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.entities.User;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.services.interfaces.IUserService;
import com.joel.newsapp.utils.BuildDTOs;
import com.joel.newsapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReporterService implements IReporterService {
    @Autowired private IReporterRepository reporterRepository;
    @Autowired private ImageService imageService;
    @Autowired private IUserService userService;
    @Autowired private Utils utils;
    @Autowired private BuildDTOs dtos;

    @Override
    public ReporterInfoDTO save(RegisterReporterDTO reporterDTO) throws Exception {
        User user = this.userService.register(reporterDTO.getUserDTO());

        Reporter rep = Reporter.builder()
                .user(user)
                .monthlySalary(reporterDTO.getMonthlySalary())
                .build();
        Reporter r = this.reporterRepository.save(rep);
        return this.createReporterInfoDTO(r);

    }

    @Override
    public ReporterInfoDTO getById(String id) throws NotFoundException {
       Optional<Reporter> repO = this.reporterRepository.findById(id);
        if (repO.isPresent()) {
            Reporter reporter = repO.get();
            return this.createReporterInfoDTO(reporter);
        }
        throw new NotFoundException("Reporter not found");
    }

    @Override
    public ReporterInfoDTO edit(EditReporterDTO reporterEditReqDTO) throws NotFoundException {
        return null;
    }

    @Override
    public String deleteById(String s) {
        return null;
    }

    @Override
    public List<ReporterInfoDTO> getAllReporters() {
        List<Reporter> reporters = this.reporterRepository.findAll();
        List<ReporterInfoDTO> reportersDTO = new ArrayList<>();
        for (Reporter r : reporters) {
            reportersDTO.add(this.createReporterInfoDTO(r));
        }
        return reportersDTO;
    }


    @Override
    public Reporter findByEmail(String email) throws NotFoundException {
        Optional<Reporter> reporterOptional = this.reporterRepository.findByUser_Email(email);
        if (reporterOptional.isPresent()) {
            return reporterOptional.get();
        }
       throw new NotFoundException("Reporter not found");
    }

    @Override
    public Reporter findById(String id) throws NotFoundException {
        Optional<Reporter> reporterOptional = this.reporterRepository.findById(id);
        if (reporterOptional.isPresent()) {
            return  reporterOptional.get();
        }
        throw new NotFoundException("Reporter not found");
    }

    @Override
    public Reporter findByUserId(String id) throws NotFoundException {
        Optional<Reporter> reporterOptional = this.reporterRepository.findByUser_Id(id);
        if (reporterOptional.isPresent()) {
            return  reporterOptional.get();
        }
        throw new NotFoundException("Reporter not found");
    }

    @Override
    public EmployeeDTO reporterInfo(String id) throws NotFoundException {
        return null;
    }

    @Override
    public List<EmployeeDTO> allReporterInfo() {
       List<Reporter> reporters = this.reporterRepository.findAll();
       List<EmployeeDTO> employee = new ArrayList<>();

       for (Reporter r : reporters) {
           employee.add(this.dtos.createEmployeeInfo(r));
       }

       return employee;
    }

    public String updateSalaryAndEnabled(Integer salary, boolean active, String id){
       /* int filas =this.reporterRepository.updateSalaryAndEnabled(salary, active, id);
        if (filas != 0) {
            System.out.println("encontro");
            return "Updated";
        }*/
        System.out.println("no encontro");
        return "Updated failed";
    }

    private ReporterInfoDTO createReporterInfoDTO(Reporter reporter) {
        User user = reporter.getUser();
        UserInfoDTO reporterInfo = UserInfoDTO.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .id(user.getId())
                .build();
        if (user.getImage() == null) {
            reporterInfo.setProfilePictureId("user_img");
        } else {
            reporterInfo.setProfilePictureId(user.getImage().getId());
        }
        return new ReporterInfoDTO(reporter.getMonthlySalary(), reporter.getEnabled(), reporter.getId(), reporterInfo);
    }


    public void adminRegister(AdminRegisterEmployeeDTO reporter) {
    }

}
