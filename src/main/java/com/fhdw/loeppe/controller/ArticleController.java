package com.fhdw.loeppe.controller;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.service.ArticleService;
import com.fhdw.loeppe.util.Mapper;
import com.fhdw.loeppe.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = "/article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Article getArticleById(@PathVariable UUID id) {
        var article = articleService.getArticle(id);
        if (article == null) throw new ResourceNotFoundException();

        return article;
    }

    @GetMapping(value = "/article", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @PostMapping(path = "/article",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Article postArticle(@RequestBody Article article){
        return mapper.map(articleService.saveArticle(article), Article.class);
    }

    @PutMapping(path = "/article",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Article putArticle(@RequestBody Article article){
        return mapper.map(articleService.saveArticle(article), Article.class);
    }

    @DeleteMapping(value = "/article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteArticleById(@PathVariable UUID id) {
        var article = articleService.getArticle(id);
        if (article == null) throw new ResourceNotFoundException();

        articleService.deleteArticle(article);
    }
}
