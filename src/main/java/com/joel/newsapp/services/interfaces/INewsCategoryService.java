package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.newscategory.NCategoryEditReqDTO;
import com.joel.newsapp.dtos.newscategory.NCategoryPostReqDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;

import java.util.List;

public interface INewsCategoryService extends ICrudService<NewsCategory, NCategoryPostReqDTO, NCategoryEditReqDTO, String> {
    List<String> getAllCategories();
    List<News> latest(int quantity);
}
