package com.joel.newsapp.dtos.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsEditReqDTO extends NewsPostReqDTO{
    private Long id;
    private Boolean isAdmin;
    public NewsEditReqDTO(String title, String body, List<String> categories, String reporterUsername, MultipartFile image, Long id, Boolean isAdmin) {
        super(title, body, categories, reporterUsername, image);
        this.id = id;
        this.isAdmin = isAdmin;
    }
}
