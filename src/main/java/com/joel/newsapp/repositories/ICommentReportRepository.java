package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.comment.CommentByReportsDTO;
import com.joel.newsapp.entities.Comment;
import com.joel.newsapp.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentReportRepository extends JpaRepository<Report, String> {
/*
    @Query("SELECT r.id AS id, COUNT(r.comment.id) AS quantity FROM Reporter r")
    @EntityGraph(attributePaths = {"id", "quantity"})
    List<CommentByReportsDTO> comments();*/

    @Query("SELECT new com.joel.newsapp.dtos.comment.CommentByReportsDTO(r.comment AS comment, COUNT(*) AS quantity) FROM Report r GROUP BY r.comment.id")
    Page<CommentByReportsDTO> comments(Pageable page);


}
