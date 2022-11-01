package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import com.fhdw.loeppe.util.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final ArticleMapper articleMapper;

    public void createArticle(Article article){
        ArticleEntity entity = new ArticleEntity();
        articleMapper.map(article, entity);
        repository.saveAndFlush(entity);
    }

    public void saveAllArticles(List<Article> articles){
        List<ArticleEntity> entitys = new ArrayList<>();
        articleMapper.map(articles, entitys);
        repository.saveAll(entitys);
    }

    public Optional<Article> readArticle(long id) {
        Optional<ArticleEntity> entity = repository.findById(id);
        Optional<Article> article = Optional.of(new Article());
        articleMapper.map(entity, article);

        return article;
    }

    public List<Article> readAllArticle(){
        List<ArticleEntity> entities = repository.findAll();
        ArrayList<Article> articles = articleMapper.mapAll(entities);

        return articles;
    }

    public void updateArticle(Article article) {
        createArticle(article);
    }

    public void deleteArticle(long id) {
        repository.deleteById(id);
    }

    public void deleteAllArticles() {
        repository.deleteAll();
    }
}

//TODO: ServiceTest
