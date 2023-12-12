package com.joel.newsapp.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDashboardPageDTO {
    private List<CommentDashboardDTO> comments;
    private int totalPages;
    private Long totalElements;
}
