package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsCategoryRepository extends JpaRepository<NewsCategory, String> {
}
