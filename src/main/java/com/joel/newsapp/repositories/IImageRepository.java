package com.joel.newsapp.repositories;

import com.joel.newsapp.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, String> {
}
