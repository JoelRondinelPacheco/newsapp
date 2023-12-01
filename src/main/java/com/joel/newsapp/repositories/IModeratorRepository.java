package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Admin;
import com.joel.newsapp.entities.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IModeratorRepository extends JpaRepository<Moderator, String> {
    Optional<Moderator> findByUser_Id(String id);

    List<Moderator> findAllByEnabledAndUser_Verified(Boolean enabled, Boolean verified);

    List<Moderator> findAllByEnabled(boolean b);
}
