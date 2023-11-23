package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.PasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPasswordTokenRepository extends JpaRepository<PasswordToken, String> {
    Optional<PasswordToken> findByToken(String token);
}
