package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsSearchReqDTO;
import com.joel.newsapp.dtos.news.NewsSearchResDTO;
import com.joel.newsapp.exceptions.NotFoundException;
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

    @GetMapping("/main")
    public ResponseEntity<List<NewsSearchResDTO>> newsAllCategories(@RequestParam String reporterName, @RequestParam String reporterLastname, @RequestParam String newsTitle, @RequestParam String newsDate) {

        NewsSearchReqDTO body = NewsSearchReqDTO.builder()
                .reporterName(reporterName)
                .reporterLastname(reporterLastname)
                .newsTitle(newsTitle)
                .build();

        if (!newsDate.isBlank()){
            try {
                LocalDate date = LocalDate.parse(newsDate);
                body.setNewsDate(date);
                System.out.println("formateo fech: " + date);
            } catch (Exception e) {
                body.setNewsDate(null);
            }
        }

            List<NewsSearchResDTO> news = this.newsService.searchAllNews(body);
        System.out.println(news.get(0).getNewsTitle());
            return new ResponseEntity<>(news, HttpStatus.OK);


    }
}
