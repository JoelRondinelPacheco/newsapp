package com.joel.newsapp.dtos.news;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class NewsViewDTO extends NewsHomeDTO {
    private String newsImage;
    private String newsImageCaption;
    private String newsBody;
    private String newsCategoryId;
    private NewsHomeDTO newsDetails;
}
