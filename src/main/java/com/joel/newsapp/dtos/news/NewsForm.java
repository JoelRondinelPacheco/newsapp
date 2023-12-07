package com.joel.newsapp.dtos.news;

import com.joel.newsapp.dtos.newscategory.CategoriesFormDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class NewsForm {
    private String title;
    private String subtitle;
    private String imageCaption;
    private String body;
    private List<CategoriesFormDTO> categories;
    private String mainCategoryId;
    private String reporterUsername;
    private String imageId;
}
