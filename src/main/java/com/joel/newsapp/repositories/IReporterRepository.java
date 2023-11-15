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
    //Reporter info dto
    @Query("SELECT new com.joel.newsapp.dtos.reporter.ReporterInfoDTO(r.name, r.lastname, r.displayName, r.email, r.image.id AS profilePictureId, r.role, r.enabled, r.monthlySalary, r.id) FROM Reporter r WHERE r.id = :reporterId")
    Optional<ReporterInfoDTO> getReporterInfoDTO(@Param("reporterId") String reporterId);
    //All reporter info dto
    @Query("SELECT new com.joel.newsapp.dtos.reporter.ReporterInfoDTO(r.name, r.lastname, r.displayName, r.email, r.image.id AS profilePictureId, r.role, r.enabled, r.monthlySalary, r.id) FROM Reporter r")
    List<ReporterInfoDTO> getAllReporterInfo();
    Optional<Reporter> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE Reporter r SET r.monthlySalary = :salary, r.enabled = :enabled WHERE r.id = :idU")
    int updateSalaryAndEnabled(@Param("salary") Integer salary, @Param("enabled") Boolean enabled, @Param("idU") String id);
}
