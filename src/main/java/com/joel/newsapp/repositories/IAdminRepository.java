package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByUser_Id(String id);
}
