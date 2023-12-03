package com.joel.newsapp.dtos.newscategory;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CategoryDTO {
    private String name;
    private String id;
    private Boolean selected;
}
