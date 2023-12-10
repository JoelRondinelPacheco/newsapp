package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Admin;
import com.joel.newsapp.entities.Reporter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByUser_Id(String id);
    Page<Admin> findAllByEnabledAndUser_Verified(Boolean enabled, Boolean verified, Pageable page);
    Page<Admin> findAllByEnabled(Boolean enabled, Pageable page);
}
