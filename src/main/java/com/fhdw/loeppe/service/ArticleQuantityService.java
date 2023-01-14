package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.ArticleQuantity;
import com.fhdw.loeppe.entity.ArticleQuantityEntity;
import com.fhdw.loeppe.repo.ArticleQuantityRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleQuantityService {

    private final ArticleQuantityRepository repository;
    private final Mapper mapper;

    public ArticleQuantityEntity saveArticleQuantity(ArticleQuantity articleQuantity){
        return repository.saveAndFlush(mapper.map(articleQuantity, ArticleQuantityEntity.class));
    }

    public void saveAllArticleQuantitys(List<ArticleQuantity> articleQuantitys){
        repository.saveAllAndFlush(Collections.singletonList(mapper.map(articleQuantitys, ArticleQuantityEntity.class)));
    }

    public ArticleQuantity getArticleQuantity(UUID id) {
        return mapper.map(repository.findById(id), ArticleQuantity.class);
    }

    public List<ArticleQuantity> getAllArticleQuantitys(){
        return mapper.mapAll(repository.findAll(), ArticleQuantity.class);
    }

    public void deleteArticleQuantity(ArticleQuantity articleQuantity) {
        repository.delete(mapper.map(articleQuantity, ArticleQuantityEntity.class));
    }

    public void deleteAllArticleQuantitys() {
        repository.deleteAll();
    }
}
