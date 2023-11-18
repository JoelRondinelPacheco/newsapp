package com.joel.newsapp.dtos.users;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EditUserDTO {
    private String id;
    private String name;
    private String lastname;
    private MultipartFile profilePicture;

}
