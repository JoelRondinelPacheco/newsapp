package com.joel.newsapp.dtos.news;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NewsHomeDTO {
    private String newsId;
    private String newsTitle;
    private String newsDate;
    private String newsHour;
    private String newsSubtitle;
    private String newsCategory;
    private String reporterId;
    private String reporterName;
    private String reporterEmail;
}
