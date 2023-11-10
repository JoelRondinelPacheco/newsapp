package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.newscategory.NCategoryEditReqDTO;
import com.joel.newsapp.dtos.newscategory.NCategoryPostReqDTO;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface INewsCategoryService extends ICrudService<NewsCategory, NCategoryPostReqDTO, NCategoryEditReqDTO, String> {
    List<String> getAllCategories();
    NewsCategory findByName(String name) throws NotFoundException;
}
