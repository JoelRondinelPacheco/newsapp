package com.joel.newsapp.dtos.news;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NewsSearchReqDTO {
    private String reporterName;
    private String newsTitle;
    private String newsDate;
}
