package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.exceptions.NotFoundException;

public interface ICommentReactionService {
    String like(String email, String commentId, Boolean isPositive) throws NotFoundException;
}
