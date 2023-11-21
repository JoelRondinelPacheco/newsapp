package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModeratorRepository extends JpaRepository<Moderator, String> {
}
