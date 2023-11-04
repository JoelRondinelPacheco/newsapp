package com.joel.newsapp.services;

import com.joel.newsapp.dtos.newscategory.NCategoryEditReqDTO;
import com.joel.newsapp.dtos.newscategory.NCategoryPostReqDTO;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsCategoryRepository;
import com.joel.newsapp.services.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class NewsCategoryService implements ICrudService<NewsCategory, NCategoryPostReqDTO, NCategoryEditReqDTO, String> {

    @Autowired
    private INewsCategoryRepository newsCategoryRepository;
    @Override
    public NewsCategory save(NCategoryPostReqDTO nCategoryPostReqDTO) {
        return null;
    }

    @Override
    public NewsCategory getById(String id) throws NotFoundException {
        Optional<NewsCategory> categoryOptional = this.newsCategoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            NewsCategory category = categoryOptional.get();
            return category;
        }
        throw new NotFoundException("Category not found");
    }

    @Override
    public NewsCategory edit(NCategoryEditReqDTO nCategoryEditReqDTO) throws NotFoundException {
        return null;
    }

    @Override
    public String deleteById(String s) {
        return null;
    }
}
