package com.joel.newsapp.services;

import com.joel.newsapp.dtos.news.NewsEditReqDTO;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.entities.*;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsRepository;
import com.joel.newsapp.services.interfaces.ICrudService;
import com.joel.newsapp.services.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {
    @Autowired
    private INewsRepository newsRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ReporterService reporterService;
    @Autowired
    private NewsCategoryService newsCategoryService;

    @Override
    public News save(NewsPostReqDTO newsDTO) throws NotFoundException{
        Reporter reporter = this.reporterService.findByEmail(newsDTO.getReporterUsername());
        Image image = this.imageService.save(newsDTO.getImage());
        List<NewsCategory> categories = this.findCategories(newsDTO.getCategories());
        News news = new News(newsDTO.getTitle(), newsDTO.getSubtitle(), newsDTO.getImageCaption(), newsDTO.getBody(), categories, reporter, image);
        return this.newsRepository.save(news);
    }


    @Override
    public News getById(String id) throws NotFoundException {
        Optional<News> newsOptional = this.newsRepository.findById(id);
        if (newsOptional.isPresent()) {
            return newsOptional.get();
        }
        throw new NotFoundException("News not found");
    }

    @Override
    public News edit(NewsEditReqDTO body) throws Exception {
        try {
            News news = this.getById(body.getId());
            if (news.getAuthor().getEmail().equals(body.getReporterUsername()) || body.getIsAdmin()) {
                news.setTitle(body.getTitle());
                news.setSubtitle(body.getSubtitle());
                news.setImageCaption(body.getImageCaption());
                news.setBody(body.getBody());

                String imageId = news.getImage().getId();
                this.imageService.update(body.getImage(), imageId);
                List<NewsCategory> categories = this.findCategories(body.getCategories());
                news.setCategories(categories);
                return this.newsRepository.save(news);
            }
        } catch (NotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        throw new NotFoundException("News not found");
    }

    @Override
    public String deleteById(String id) {
        this.newsRepository.deleteById(id);
        return "News deleted";
    }
    @Override
    public List<News> getAll() {
        List<News> news = this.newsRepository.findAll();
        return news;
    }
    @Override
    public List<News> getNewsByUser(String userId){
        List<News> news = this.newsRepository.getNewsByAuthorId(userId);
        return news;

    }
    @Override
    public News mainFeatured() throws NotFoundException {
        Optional<News> newsOptional = this.newsRepository.findByMainFeatured(true);
        if(newsOptional.isPresent()){
            return newsOptional.get();
        }
        throw new NotFoundException("News not found");
    }

    @Override
    public List<News> featuredByCategory(String category) throws NotFoundException {
        this.newsCategoryService.findByName(category);
        //List<News> news = this.newsRepository.findByFeaturedAndCategory_Name(true, category);
        //return news;
        return null;
    }

    @Override
    public List<News> findByCategory(String category, int quantity) {
        //return this.newsRepository.findByCategory(category, quantity);
        return null;
    }
    @Override
    public List<News> latest(int quantity) {
        Pageable pageable = PageRequest.of(0, quantity);
        return this.newsRepository.findLatest(pageable);
    }

    @Override
    public List<News> latestByCategory(String category, int quantity) throws NotFoundException {
        this.newsCategoryService.findByName(category);
        Pageable pageable = PageRequest.of(0, quantity);
       // return this.newsRepository.findByCategory_NameOrderByCreatedAtDesc(category, pageable);
        return null;
    }

    @Override
    public News categoryFeatured(String category) throws NotFoundException {
        this.newsCategoryService.findByName(category);
        //TODO MANAGE MULTIPLE MAIN FEATURED
       // List<News> news = this.newsRepository.findByFeaturedCategoryAndCategories_Name(true, category);
        //return news.get(0);
        return null;

    }

    private List<NewsCategory> findCategories(List<String> categories) {
        List<NewsCategory> newsCategories = new ArrayList<>();
        for (String categoryId : categories) {
            try {
                NewsCategory category = this.newsCategoryService.getById(categoryId);
                newsCategories.add(category);
            } catch (NotFoundException ex) {
                continue;
            }
        }
        return newsCategories;
    }
}
