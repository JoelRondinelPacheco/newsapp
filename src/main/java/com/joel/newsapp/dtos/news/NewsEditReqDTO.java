package com.joel.newsapp.dtos.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NewsEditReqDTO extends NewsPostReqDTO{
    private String id;
    private Boolean isAdmin;
    public NewsEditReqDTO(String title, String subtitle, String imageCaption, String body, List<String> categories, String reporterUsername, MultipartFile image, String id, Boolean isAdmin) {
        super(title, subtitle, imageCaption, body, categories, reporterUsername, image);
        this.id = id;
        this.isAdmin = isAdmin;
    }
}
