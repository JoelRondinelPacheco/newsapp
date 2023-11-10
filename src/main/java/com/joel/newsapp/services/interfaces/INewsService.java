package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.news.NewsEditReqDTO;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface INewsService extends ICrudService<News, NewsPostReqDTO, NewsEditReqDTO, String>{
    List<News> getAll();
    List<News> getNewsByUser(String userId);
    News featured() throws NotFoundException;
    List<News> findByCategory(String category, int quantity);
}