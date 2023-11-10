package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INewsRepository extends JpaRepository<News, String> {
    List<News> getNewsByAuthorId(String authorId);
    Optional<News> findByCategoriesCategory(String category);

    @Query("SELECT n FROM News n JOIN n.categories c WHERE c.category =:category LIMIT :limit")
    List<News> findByCategory(@Param("category") String category, @Param("limit") int limit);
}
