package com.joel.newsapp.dtos.comment;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDashboardDTO {
    private String commentId;
    private String authorId;
    private String authorName;
    private String date;
    private String hour;
    private int reports;
}
