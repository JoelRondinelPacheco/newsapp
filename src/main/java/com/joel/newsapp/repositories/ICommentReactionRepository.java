package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentReactionRepository extends JpaRepository<CommentReaction, String> {
    Optional<CommentReaction> findByComment_IdAndUser_Email(String commentId, String userEmail);
}
