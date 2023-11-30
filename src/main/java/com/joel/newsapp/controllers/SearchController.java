package com.joel.newsapp.controllers;

import com.joel.newsapp.dtos.news.NewsSearchReqDTO;
import com.joel.newsapp.dtos.news.NewsSearchResDTO;
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
                System.out.println("Fecha string: " + newsDate);
                LocalDate date = LocalDate.parse(newsDate);
                body.setNewsDate(date);
                System.out.println("formateo fech: " + date);
            } catch (Exception e) {
                body.setNewsDate(null);
            }
        }

        List<NewsSearchResDTO> newsT = this.newsService.searchAllNews(body);
        System.out.println("search controller");
        System.out.println("rname: " + reporterName);
        System.out.println("news title: " + newsTitle);
        System.out.println("news date: " + newsDate);
        List<NewsSearchResDTO> news = new ArrayList<>();
        news.add(new NewsSearchResDTO("id1", "Titulo noticia 1", "fecha1", "Categoria1", "idr1", "Reporter1"));
        news.add(new NewsSearchResDTO("id2", "Titulo noticia 2", "fecha2", "Categoria2", "idr2", "Reporter2"));
        news.add(new NewsSearchResDTO("id3", "Titulo noticia 3", "fecha3", "Categoria3", "idr3", "Reporter3"));
        news.add(new NewsSearchResDTO("id4", "Titulo noticia 4", "fecha4", "Categoria4", "idr4", "Reporter4"));
    return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
