package com.joel.newsapp.dtos.reporter;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class ReporterInfoDTO extends UserInfoDTO {
    private Double monthlySalary;
    public ReporterInfoDTO(String name, String lastname, String email, Role role, Boolean enabled, String profilePicture, Double monthlySalary) {
        super(name, lastname, email, role, enabled, profilePicture);
        this.monthlySalary = monthlySalary;
    }
}
