package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.newscategory.NCategoryEditReqDTO;
import com.joel.newsapp.dtos.newscategory.NCategoryPostReqDTO;
import com.joel.newsapp.entities.News;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface INewsCategoryService extends ICrudService<NewsCategory, String, NCategoryEditReqDTO, String> {

    NewsCategory findByName(String name) throws NotFoundException;
    List<NewsCategory> findAll();

}
