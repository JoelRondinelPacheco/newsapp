package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.comment.*;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface ICommentService  {
    Comment save(CommentPostReqDTO comment) throws NotFoundException;
    Comment getById(String id) throws NotFoundException;
    List<CommentViewDTO> getAllNewsComments(String newsId, String email) throws NotFoundException;

    CommentDashboardPageDTO getByReports(int pageNumber, int pageSize);

    void delete(String commentId) throws NotFoundException;
}
