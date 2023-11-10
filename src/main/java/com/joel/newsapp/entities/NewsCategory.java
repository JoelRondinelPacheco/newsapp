package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("category")
public class NewsCategory extends Base {
    private String name;

    @ManyToMany
    @JoinTable(name = "rel_category_news", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "news_id"))
    private List<News> news;
}
