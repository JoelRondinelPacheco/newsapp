package com.joel.newsapp.dtos.news;

import com.joel.newsapp.dtos.users.UserInfoDTO;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class NewsPaginatedDTO {
    private List<NewsHomeDTO> news;
    private int totalPages;
    private Long totalElements;
}
