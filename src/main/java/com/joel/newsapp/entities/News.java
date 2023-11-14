package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="news")
@Getter
@Setter
@NoArgsConstructor
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
    // TODO install TipTap
    private String body;
    private Boolean featured;
    private Boolean featuredCategory;
    private Boolean mainFeatured;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(mappedBy = "news")
    private List<NewsCategory> categories;

    public News(String title, String subtitle, String imageCaption, String body, List<NewsCategory> categories, Reporter author, Image image) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageCaption = imageCaption;
        this.body = body;
        this.categories = categories;
        this.author = author;
        this.image = image;
    }
}
