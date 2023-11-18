package com.joel.newsapp.dtos.reporter;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import com.joel.newsapp.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReporterInfoDTO {
    private Double monthlySalary;
    private Boolean reporterEnabled;
    private String reporterId;
    private UserInfoDTO reporterInfo;

}
