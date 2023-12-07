package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name = "categories")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class NewsCategory extends Base {
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<News> news;

    @OneToMany(mappedBy = "mainCategory")
    private List<News> mainNews;

    public NewsCategory(String name) {
        this.name = name;
    }
}
