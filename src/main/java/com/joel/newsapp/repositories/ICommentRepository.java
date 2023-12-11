package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findAllByNews_Id(String id);

    @Query("SELECT c FROM Comment c ORDER BY COUNT(c.reports) ASC")
    Page<Comment> selectByReports(Pageable page);
}
