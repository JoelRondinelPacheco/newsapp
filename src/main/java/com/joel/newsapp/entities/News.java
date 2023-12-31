package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name = "news_table")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class News extends Base{
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Reporter author;

    @OneToOne
    @JoinColumn(name="id_image", referencedColumnName = "id")
    private Image image;

    private String title;
    private String subtitle;
    private String imageCaption;
    // TODO install
    @Column(columnDefinition = "LONGTEXT")
    private String body;
    private Boolean featured;
    private Boolean featuredCategory;
    private Boolean mainFeatured;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private NewsCategory mainCategory;


    @ManyToMany
    @JoinTable(name = "rel_category_news", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "news_id"))
    private List<NewsCategory> categories;

    public News(String title, String subtitle, String imageCaption, String body, List<NewsCategory> categories, NewsCategory mainCategory, Reporter author, Image image) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageCaption = imageCaption;
        this.body = body;
        this.categories = categories;
        this.mainCategory = mainCategory;
        this.author = author;
        this.image = image;
    }

    @PrePersist
    private void prePersist(){
        this.featured = false;
        this.featuredCategory = false;
        this.mainFeatured = false;
    }
}
