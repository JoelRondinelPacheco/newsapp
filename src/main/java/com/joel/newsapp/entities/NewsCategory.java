package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@DiscriminatorValue("category")
public class NewsCategory extends Base {
    private String name;

    @ManyToMany
    @JoinTable(name = "rel_category_news", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "news_id"))
    private List<News> news;

    public NewsCategory(String name) {
        this.name = name;
    }
}
