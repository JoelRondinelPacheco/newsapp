package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INewsRepository extends JpaRepository<News, String> {
    List<News> getNewsByAuthorId(String authorId);
    Optional<News> findByMainFeatured(boolean featured);
/*
NO SE PUEDE USAR LIMIT, IMPLEMENTAR PAGEABLE
    @Query("SELECT n FROM News n JOIN n.categories c WHERE c.category =:category LIMIT :limit")
    List<News> findByCategory(@Param("category") String category, @Param("limit") int limit);
*/
    /*
    @Query("SELECT n FROM News n ORDER BY n.createdAt DESC")
    List<News> findLatest(Pageable pageable);
*/
    List<News> findByFeaturedCategory(Boolean b);

    List<News> findByFeaturedCategoryAndMainCategory_Name(boolean b, String category);
/*
    COMO BUSCAR CUANDO CATEGORIES ES UNA LISTA
    List<News> findByFeaturedAndCategory_Name(boolean featured, String category);

    List<News> findByCategory_NameOrderByCreatedAtDesc(String category, Pageable pageable);

    List<News> findByFeaturedCategoryAndCategories_Name(boolean b, String category);*/
}
