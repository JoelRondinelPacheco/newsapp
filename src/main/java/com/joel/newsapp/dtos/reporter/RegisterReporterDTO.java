package com.joel.newsapp.dtos.reporter;

import com.joel.newsapp.dtos.users.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class RegisterReporterDTO{
    private Double monthlySalary;
    private RegisterUserDTO userDTO;
    public RegisterReporterDTO(RegisterUserDTO userDTO, Double monthlySalary) {
        this.userDTO = userDTO;
        this.monthlySalary = monthlySalary;
    }

}
