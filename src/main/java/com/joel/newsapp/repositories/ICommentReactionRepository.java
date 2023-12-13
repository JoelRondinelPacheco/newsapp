package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.CommentReaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentReactionRepository extends JpaRepository<CommentReaction, String> {
    Optional<CommentReaction> findByComment_IdAndUser_Email(String commentId, String userEmail);

    int deleteByComment_Id(String commentId);
}
