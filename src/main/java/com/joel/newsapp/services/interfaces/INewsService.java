package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.news.*;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface INewsService extends ICrudService<News, NewsPostReqDTO, NewsEditReqDTO, String>{
    NewsPaginatedDTO getAll(int pageNumber, int pageSize);
    List<News> getNewsByUser(String userId);
    NewsHomeDTO mainFeatured() throws NotFoundException;
    List<News> featuredByCategory(String category) throws NotFoundException;
    List<FeaturedByCategoryDTO> allFeaturedByCategory();
    List<News> findByCategory(String category, int quantity);
    List<News> latest(int quantity);
    List<News> latestByCategory(String category, int quantity) throws NotFoundException;
    List<NewsSearchResDTO> searchNews(NewsSearchReqDTO body, String category);

    List<NewsSearchResDTO> searchByCategory(NewsSearchReqDTO body, String categoryId) throws NotFoundException;
    News setMainFeatured(String newsId) throws NotFoundException;

    News setCategoryFeatured(String newsId) throws NotFoundException;

    NewsViewDTO getByIdDTO(String id) throws NotFoundException;
    boolean existsById(String id) throws NotFoundException;

    List<NewsHomeDTO> getFeatured(int quantity);

    News setIsFeatured(String newsId, Boolean isFeatured) throws NotFoundException;


}
