package com.joel.newsapp.dtos.reporter;

import com.joel.newsapp.dtos.users.EditUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
public class EditReporterDTO extends EditUserDTO {
    public EditReporterDTO(String id, String name, String lastname, String displayName, MultipartFile profilePicture) {
        super(id, name, lastname, displayName,profilePicture);
    }

}
