package com.joel.newsapp.dtos.search;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AllNewsForm {
    private String reporterName;
    private String reporterLastname;
    private String newsTitle;
    private String newsCategory;
    private String newsDate;
}
