package com.joel.newsapp.dtos.news;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class FeaturedByCategoryDTO {
    private String categoryId;
    private String categoryName;
    private boolean hasFeatured;
    private NewsHomeDTO news;
}
