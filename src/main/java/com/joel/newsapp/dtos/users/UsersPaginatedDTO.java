package com.joel.newsapp.dtos.users;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UsersPaginatedDTO {
    private List<UserInfoDTO> users;
    private int totalPages;
    private Long totalElements;
}
