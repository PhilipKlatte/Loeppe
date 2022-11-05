package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final Mapper mapper;

    public void saveArticle(Article article){
        ArticleEntity entity = new ArticleEntity();
        mapper.map(article, entity);
        repository.saveAndFlush(entity);
    }

    public void saveAllArticles(List<Article> articles){
        List<ArticleEntity> entities = new ArrayList<>();

        articles.forEach(article -> {
            ArticleEntity entity = new ArticleEntity();
            mapper.map(article, entity);
            entities.add(entity);
        });

        repository.saveAll(entities);
    }

    public Optional<Article> getArticle(long id) {
        Optional<ArticleEntity> entity = repository.findById(id);
        Optional<Article> article = Optional.of(new Article());
        mapper.map(entity, article);

        return article;
    }

    public List<Article> getAllArticles(){
        List<ArticleEntity> entities = repository.findAll();

        return mapper.mapAllArticles(entities);
    }

    public void updateArticle(Article article) {
        saveArticle(article);
    }

    public void deleteArticle(long id) {
        repository.deleteById(id);
    }

    public void deleteAllArticles() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
