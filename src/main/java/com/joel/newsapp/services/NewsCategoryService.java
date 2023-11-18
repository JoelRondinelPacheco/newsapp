package com.joel.newsapp.services;

import com.joel.newsapp.dtos.newscategory.NCategoryEditReqDTO;
import com.joel.newsapp.dtos.newscategory.NCategoryPostReqDTO;
import com.joel.newsapp.entities.NewsCategory;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsCategoryRepository;
import com.joel.newsapp.services.interfaces.ICrudService;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsCategoryService implements INewsCategoryService {

    @Autowired
    private INewsCategoryRepository newsCategoryRepository;
    @Override
    public NewsCategory save(String category) {
        NewsCategory cat = new NewsCategory(category);
        return this.newsCategoryRepository.save(cat);
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
    @Override
    public List<String> getAllCategories() {
        //return this.newsCategoryRepository.findAllCategories();
        return null;
    }

    @Override
    public List<NewsCategory> findAll() {
        return this.newsCategoryRepository.findAll();
    }
    @Override
    public NewsCategory findByName(String name) throws NotFoundException {
        Optional<NewsCategory> categoryOptional = this.newsCategoryRepository.findByName(name);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new NotFoundException("Category not found");
    }
}
