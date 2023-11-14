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
    private String lastname;
    public RegisterReporterDTO(String name, String email, String password, MultipartFile profilePicture, Double monthlySalary, String lastname) {
        super(name, email, password, profilePicture);
        this.monthlySalary = monthlySalary;
        this.lastname = lastname;
    }

}
