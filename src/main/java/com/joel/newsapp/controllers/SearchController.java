package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsSearchReqDTO;
import com.joel.newsapp.dtos.news.NewsSearchResDTO;
import com.joel.newsapp.services.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private INewsService newsService;

    public ResponseEntity<List<NewsSearchResDTO>> newsAllCategories(@RequestBody NewsSearchReqDTO body) {
        List<NewsSearchResDTO> news = this.newsService.searchAllNews(body);

    }
}
