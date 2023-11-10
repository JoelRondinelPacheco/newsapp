package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsCategoryRepository extends JpaRepository<NewsCategory, String> {
    @Query("SELECT c.category FROM NewsCategory c;")
    List<String> findAllCategories();
}
