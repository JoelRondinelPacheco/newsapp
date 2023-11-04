package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Reporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReporterRepository extends JpaRepository<Reporter, String> {
    Optional<Reporter> findByEmail(String email);
}
