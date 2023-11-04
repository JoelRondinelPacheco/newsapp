package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, String> {
    @Query("SELECT comment FROM Comment comment WHERE comment.news = :newsId")
    List<Comment> getNewsComments(@Param("newsId") News newsId);
}
