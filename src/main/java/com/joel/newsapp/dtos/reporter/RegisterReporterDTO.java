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
public class RegisterReporterDTO extends RegisterUserDTO {
    private Double monthlySalary;
    public RegisterReporterDTO(String name, String lastname, String email, String password, MultipartFile profilePicture, Double monthlySalary) {
        super(name, lastname, email, password, profilePicture);
        this.monthlySalary = monthlySalary;
    }

}
