package com.joel.newsapp.dtos.news;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class NewsSearchReqDTO {
    private String reporterName;
    private String reporterLastname;
    private String newsTitle;
    private LocalDate newsDate;
}
