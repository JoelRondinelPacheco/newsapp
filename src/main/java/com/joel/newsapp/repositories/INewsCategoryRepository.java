package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INewsCategoryRepository extends JpaRepository<NewsCategory, String> {
    /*@Query("SELECT c.name FROM NewsCategory c;")
    List<String> findAllCategories();*/

    Optional<NewsCategory> findByName(String name);
}
