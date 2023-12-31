package com.joel.newsapp.repositories;

import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.entities.Reporter;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Optional<Reporter> findByUser_Id(String id);
    Page<Reporter> findAllByEnabledAndUser_Verified(Boolean enabled, Boolean verified, Pageable page);
    Page<Reporter> findAllByEnabled(Boolean enabled, Pageable page);
}
