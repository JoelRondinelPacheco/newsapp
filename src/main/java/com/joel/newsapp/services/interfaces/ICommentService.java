package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.comment.CommentEditReqDTO;
import com.joel.newsapp.dtos.comment.CommentPostReqDTO;
import com.joel.newsapp.dtos.comment.CommentViewDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface ICommentService  {
    List<CommentViewDTO> getAllNewsComments(String newsId) throws NotFoundException;

}
