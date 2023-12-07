package com.joel.newsapp.dtos.news;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class NewsPostReqDTO {
    private String title;
    private String subtitle;
    private String imageCaption;
    private String body;
    private List<String> categories;
    private String mainCategory;
    private String reporterUsername;
    private MultipartFile image;
}
