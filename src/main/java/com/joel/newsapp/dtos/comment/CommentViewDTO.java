package com.joel.newsapp.dtos.comment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CommentViewDTO {
    private String commentId;
    private String authorId;
    private String authorName;
    private String comment;
    private String hour;
    private String date;
    private boolean user;
    private boolean isReported;
    private boolean like;
    private boolean dislike;
}
