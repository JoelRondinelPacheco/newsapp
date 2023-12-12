package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReportRepository extends JpaRepository<Report, String> {
    Optional<Report> findByComment_IdAndUser_Email(String commentId, String userEmail);
}
