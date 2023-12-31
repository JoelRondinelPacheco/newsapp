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
    private Boolean change;
    public NewsEditReqDTO(String title, String subtitle, String imageCaption, String body, List<String> categories, String mainCategory, String reporterUsername, MultipartFile image, String id, Boolean isAdmin, Boolean change) {
        super(title, subtitle, imageCaption, body, categories, mainCategory, reporterUsername, image);
        this.id = id;
        this.isAdmin = isAdmin;
        this.change = change;
    }
}
