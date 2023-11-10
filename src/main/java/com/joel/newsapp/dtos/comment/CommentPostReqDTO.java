package com.joel.newsapp.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentPostReqDTO {
    private String comment;
    private String news_id;
    private String user_email;
}
