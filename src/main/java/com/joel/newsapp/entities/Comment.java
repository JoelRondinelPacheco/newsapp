package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity(name = "comments")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment extends Base {
    private String comment;
    private boolean reviewed;

    @OneToMany(mappedBy = "comment")
    private List<CommentReaction> reactions;

    @OneToMany(mappedBy = "comment")
    private List<Report> reports;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User authorComment;

    @PrePersist
    private void prePersist(){
        this.reviewed = false;
    }

    public Comment(String comment, News news, User authorComment) {
        this.comment = comment;
        this.news = news;
        this.authorComment = authorComment;
    }
}
