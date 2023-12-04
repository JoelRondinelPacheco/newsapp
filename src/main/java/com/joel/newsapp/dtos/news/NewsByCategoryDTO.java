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
}
