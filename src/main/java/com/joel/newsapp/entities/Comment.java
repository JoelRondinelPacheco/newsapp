package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
public class Comment extends Base {
    private String comment;
    private int complaints;
    private int positiveScore;
    private int negativeScore;
    private boolean reviewed;
    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorComment;

    @PrePersist
    private void prePersist(){
        this.complaints = 0;
        this.positiveScore = 0;
        this.negativeScore = 0;
        this.reviewed = false;
    }

    public Comment(String comment, News news, User authorComment) {
        this.comment = comment;
        this.news = news;
        this.authorComment = authorComment;
    }
}
