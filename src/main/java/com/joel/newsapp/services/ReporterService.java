package com.joel.newsapp.services;

import com.joel.newsapp.dtos.reporter.EditReporterDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.dtos.users.AdminRegisterReporterDTO;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.entities.Image;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.services.interfaces.IReporterService;
import com.joel.newsapp.utils.Role;
import com.joel.newsapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporterService implements IReporterService {
    @Autowired
    private IReporterRepository reporterRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Utils utils;

    @Override
    public ReporterInfoDTO save(RegisterReporterDTO reporterDTO) throws Exception {

        String pass = this.utils.encryptPassword(reporterDTO.getPassword());

        Reporter rep = Reporter.builder()
                .name(reporterDTO.getName())
                .lastname(reporterDTO.getLastname())
                .displayName("")
                .email(reporterDTO.getEmail())
                .monthlySalary(reporterDTO.getMonthlySalary())
                .role(Role.REPORTER)
                .password(pass)
                .build();

        if (!reporterDTO.getProfilePicture().isEmpty()) {
            Image image = this.imageService.save(reporterDTO.getProfilePicture());
            rep.setImage(image);
        } else {
            Image img = this.imageService.defaultImage();
            rep.setImage(img);
        }
        Reporter r = this.reporterRepository.save(rep);
        return this.createReporterInfoDTO(r);

    }

    @Override
    public ReporterInfoDTO getById(String id) throws NotFoundException {
        Optional<ReporterInfoDTO> repO = this.reporterRepository.getReporterInfoDTO(id);
        if (repO.isPresent()) {
            return repO.get();
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
        return this.reporterRepository.getAllReporterInfo();
    }

    @Override
    public ReporterInfoDTO adminRegister(AdminRegisterReporterDTO reporterDTO) {
        String password = this.utils.encryptPassword(reporterDTO.getPassword());
        Reporter reporter = Reporter.builder()
                .name(reporterDTO.getName())
                .lastname(reporterDTO.getLastname())
                .displayName(reporterDTO.getName() + "." + reporterDTO.getLastname())
                .email(reporterDTO.getEmail())
                .password(password)
                .monthlySalary(reporterDTO.getMonthlySalary())
                .role(Role.REPORTER)
                .build();
        Image img = this.imageService.defaultImage();
        reporter.setImage(img);
        Reporter reporterSaved = this.reporterRepository.save(reporter);
        return this.reporterRepository.getReporterInfoDTO(reporterSaved.getId()).get();
    }


    public Reporter findByEmail(String email) throws NotFoundException {
        Optional<Reporter> reporterOptional = this.reporterRepository.findByEmail(email);
        if (reporterOptional.isPresent()) {
            return reporterOptional.get();
        }
        throw new NotFoundException("Reporter not found");
    }

    public String updateSalaryAndEnabled(Integer salary, boolean active, String id){
        int filas =this.reporterRepository.updateSalaryAndEnabled(salary, active, id);
        if (filas != 0) {
            System.out.println("encontro");
            return "Updated";
        }
        System.out.println("no encontro");
        return "Updated failed";
    }

    private ReporterInfoDTO createReporterInfoDTO(Reporter reporter) {
            return new ReporterInfoDTO(reporter.getName(), reporter.getLastname(), reporter.getDisplayName(), reporter.getEmail(), reporter.getImage().getId(), reporter.getRole(), reporter.getEnabled(), reporter.getMonthlySalary(), reporter.getId());
    }


}
