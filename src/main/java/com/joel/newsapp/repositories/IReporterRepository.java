package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.entities.Reporter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReporterRepository extends JpaRepository<Reporter, String> {
    Optional<Reporter> findByUser_Email(String email);
}
