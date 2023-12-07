package com.joel.newsapp.dtos.newscategory;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CategoriesFormDTO {
    private String name;
    private String id;
    private boolean checked;
}
