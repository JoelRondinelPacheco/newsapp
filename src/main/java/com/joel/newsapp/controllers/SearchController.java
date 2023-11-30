package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsSearchReqDTO;
import com.joel.newsapp.dtos.news.NewsSearchResDTO;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.INewsCategoryService;
import com.joel.newsapp.services.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private INewsCategoryService categoryService;

    @GetMapping("/main")
    public ResponseEntity<List<NewsSearchResDTO>> newsAllCategories(@RequestParam String reporterName, @RequestParam String reporterLastname, @RequestParam String newsTitle, @RequestParam String newsDate) {

        NewsSearchReqDTO body = this.createSearchDTO(reporterName, reporterLastname, newsTitle, newsDate);

        List<NewsSearchResDTO> news = this.newsService.searchNews(body, "");
        System.out.println(news.get(0).getNewsTitle());
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<NewsSearchResDTO>> newsByCategory(@RequestParam String reporterName, @RequestParam String reporterLastname, @RequestParam String newsTitle, @RequestParam String newsDate, @RequestParam String categoryId) {
       /* NewsSearchReqDTO body = this.createSearchDTO(reporterName, reporterLastname, newsTitle, newsDate);
        List<NewsSearchResDTO> news = new ArrayList<>();

        if (!this.categoryService.exists(categoryId)) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        }

        news = this.newsService.searchNews(body, categoryId);
        return new ResponseEntity<>(news, HttpStatus.OK);*/
        System.out.println("busqueda categoria: " + categoryId);
        return null;
    }

    private NewsSearchReqDTO createSearchDTO(String reporterName, String reporterLastname, String newsTitle, String newsDate) {
        NewsSearchReqDTO dto = NewsSearchReqDTO.builder()
                .reporterName(reporterName)
                .reporterLastname(reporterLastname)
                .newsTitle(newsTitle)
                .build();

        if (!newsDate.isBlank()) {
            try {
                LocalDate date = LocalDate.parse(newsDate);
                dto.setNewsDate(date);
                System.out.println("formateo fech: " + date);
            } catch (Exception e) {
                dto.setNewsDate(null);
            }
        }
        return dto;
    }
}
