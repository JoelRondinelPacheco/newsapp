package com.joel.newsapp.services;

import com.joel.newsapp.dtos.news.FeaturedByCategoryDTO;
import com.joel.newsapp.dtos.news.NewsEditReqDTO;
import com.joel.newsapp.dtos.news.NewsPostReqDTO;
import com.joel.newsapp.entities.*;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.INewsRepository;
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
    private ReporterService employeeService;
    @Autowired
    private NewsCategoryService newsCategoryService;

    @Override
    public News save(NewsPostReqDTO newsDTO) throws NotFoundException{
        Reporter reporter = this.employeeService.findByEmail(newsDTO.getReporterUsername());
        Image image = this.imageService.save(newsDTO.getImage());
        List<NewsCategory> categories = this.findCategories(newsDTO.getCategories());
        NewsCategory mainCategory = this.newsCategoryService.getById(newsDTO.getMainCategory());
        News news = new News(newsDTO.getTitle(), newsDTO.getSubtitle(), newsDTO.getImageCaption(), newsDTO.getBody(), categories, mainCategory, reporter, image);
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
            if (news.getAuthor().getUser().getEmail().equals(body.getReporterUsername()) || body.getIsAdmin()) {
                news.setTitle(body.getTitle());
                news.setSubtitle(body.getSubtitle());
                news.setImageCaption(body.getImageCaption());
                news.setBody(body.getBody());

                String imageId = news.getImage().getId();
                this.imageService.update(body.getImage(), imageId);
                List<NewsCategory> categories = this.findCategories(body.getCategories());
                NewsCategory mainCategory = this.newsCategoryService.getById(body.getMainCategory());
                news.setCategories(categories);
                news.setMainCategory(mainCategory);
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
    public List<News> getNewsByUser(String email){
        List<News> news = this.newsRepository.findByAuthor_User_email(email);
        return news;

    }
    @Override
    public News mainFeatured() throws NotFoundException {
        Optional<News> newsOptional = this.newsRepository.findByMainFeatured(true);
        if(newsOptional.isPresent()){
            return newsOptional.get();
        }
        throw new NotFoundException("Main featured new not found");
    }


    @Override
    public List<News> featuredByCategory(String category) throws NotFoundException {
        // TODO CKECK CATEGORY EXISTS
        List<News> news = this.newsRepository.findByFeaturedCategoryAndMainCategory_Name(true, category);
        return news;
    }

    @Override
    public List<FeaturedByCategoryDTO> allFeaturedByCategory() {
        List<NewsCategory> categories = this.newsCategoryService.findAll();
        List<FeaturedByCategoryDTO> featured = new ArrayList<>();
        for (NewsCategory c : categories) {
            FeaturedByCategoryDTO featuredC = FeaturedByCategoryDTO.builder()
                    .categoryId(c.getId())
                    .categoryName(c.getName())
                    .build();
            List<News> allNews = this.newsRepository.findByFeaturedCategoryAndMainCategory_Name(true, c.getName());
            if (!allNews.isEmpty()) {
                News featuredNew = allNews.get(0);
                featuredC.setHasFeatured(true);
                featuredC.setNewsId(featuredNew.getId());
                featuredC.setNewsTitle(featuredNew.getTitle());
                featuredC.setAuthor(featuredNew.getAuthor().getUser().getDisplayName());
                featured.add(featuredC);
            }
            featuredC.setHasFeatured(false);
            featuredC.setNewsId("");
            featuredC.setNewsTitle("");
            featuredC.setAuthor("");
            featured.add(featuredC);
        }
       return featured;

    }

    @Override
    public List<News> findByCategory(String categoryId, int quantity) {
        Pageable page = PageRequest.of(0, quantity);
        List<News> news = this.newsRepository.findByMainCategory_Id(categoryId, page);
        return news;
    }
    @Override
    public List<News> latest(int quantity) {
        Pageable pageable = PageRequest.of(0, quantity);
        return this.newsRepository.findAllByOrderByCreatedAtAsc();

    }

    @Override
    public List<News> latestByCategory(String category, int quantity) throws NotFoundException {
        this.newsCategoryService.findByName(category);
        Pageable pageable = PageRequest.of(0, quantity);
       // return this.newsRepository.findByCategory_NameOrderByCreatedAtDesc(category, pageable);
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
