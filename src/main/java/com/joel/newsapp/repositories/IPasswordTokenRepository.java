package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasswordTokenRepository extends JpaRepository<PasswordToken, String> {
}
