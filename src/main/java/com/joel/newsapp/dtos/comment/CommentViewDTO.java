package com.joel.newsapp.dtos.comment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CommentViewDTO {
    private String authorId;
    private String authorName;
    private String comment;
    private int negativeScore;
    private int positiveScore;
    private String hour;
    private String date;
}
