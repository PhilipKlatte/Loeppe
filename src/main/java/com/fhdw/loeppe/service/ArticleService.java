package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final Mapper mapper;

    public void saveArticle(Article article){
        repository.saveAndFlush(mapper.map(article, ArticleEntity.class));
    }

    public void saveAllArticles(List<Article> articles){
        repository.saveAllAndFlush(Collections.singletonList(mapper.map(articles, ArticleEntity.class)));
    }


    public Article getArticle(UUID id) {
        return mapper.map(repository.findById(id), Article.class);
    }

    public List<Article> getAllArticles(){
        return mapper.mapAll(repository.findAll(), Article.class);
    }

    public void deleteArticle(Article article) {
        repository.delete(mapper.map(article, ArticleEntity.class));
    }

    public void deleteAllArticles() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
