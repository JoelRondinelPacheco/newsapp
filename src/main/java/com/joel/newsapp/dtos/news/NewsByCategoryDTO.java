package com.joel.newsapp.dtos.news;

import com.joel.newsapp.entities.News;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class NewsByCategoryDTO {
    private String categoryId;
    private String categoryName;
    private List<NewsHomeDTO> news;
    private Boolean noNews;

    public NewsByCategoryDTO(String categoryId, String categoryName, List<NewsHomeDTO> news) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.news = news;
    }
}
