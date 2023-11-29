package com.joel.newsapp.dtos.news;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NewsSearchResDTO {
    private String newsId;
    private String newsTitle;
    private String newsDate;
    private String newsCategory;
    private String reporterId;
    private String reporterName;
}
