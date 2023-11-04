package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {
    List<News> getNewsByAuthorId(Long authorId);
}
