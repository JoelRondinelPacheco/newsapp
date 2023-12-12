package com.joel.newsapp.dtos.comment;

import com.joel.newsapp.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentByReportsDTO {
    private Comment comment;
    private Long quantity;
}
