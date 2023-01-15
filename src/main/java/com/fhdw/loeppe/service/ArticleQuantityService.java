package com.fhdw.loeppe.service;

import com.fhdw.loeppe.dto.Article;
import com.fhdw.loeppe.dto.ArticleQuantity;
import com.fhdw.loeppe.dto.Order;
import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.entity.ArticleQuantityEntity;
import com.fhdw.loeppe.entity.OrderEntity;
import com.fhdw.loeppe.repo.ArticleQuantityRepository;
import com.fhdw.loeppe.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleQuantityService {

    private final ArticleQuantityRepository repository;
    private final Mapper mapper;

    public ArticleQuantityEntity saveArticleQuantity(ArticleQuantity articleQuantity){
        var orderEntity = mapper.map(articleQuantity.getOrder(), OrderEntity.class);
        var articleEntity = articleQuantity.getArticle() != null ? mapper.map(articleQuantity.getArticle(), ArticleEntity.class) : null;
        var articleQuantityEntity = mapper.map(articleQuantity, ArticleQuantityEntity.class);
        articleQuantityEntity.setOrderEntity(orderEntity);
        articleQuantityEntity.setArticleEntity(articleEntity);

        return repository.saveAndFlush(articleQuantityEntity);
    }

    public void saveAllArticleQuantitys(List<ArticleQuantity> articleQuantitys){
        repository.saveAllAndFlush(Collections.singletonList(mapper.map(articleQuantitys, ArticleQuantityEntity.class)));
    }

    public ArticleQuantity getArticleQuantity(UUID id) {
        return mapper.map(repository.findById(id), ArticleQuantity.class);
    }

    public List<ArticleQuantity> getAllArticleQuantities(){
        List<ArticleQuantity> articleQuantities = new ArrayList<>();

        var articleQuantityEntities = repository.findAll();
        articleQuantityEntities.forEach(articleQuantityEntity -> {
            Article article = new Article();
            article.setDescription(articleQuantityEntity.getArticleEntity().getDescription());
            article.setName(articleQuantityEntity.getArticleEntity().getName());
            article.setId(articleQuantityEntity.getArticleEntity().getId());
            article.setPrice(articleQuantityEntity.getArticleEntity().getPrice());
            Order order = mapper.map(articleQuantityEntity.getOrderEntity(), Order.class);
            ArticleQuantity articleQuantity = mapper.map(articleQuantityEntity, ArticleQuantity.class);
            articleQuantity.setOrder(order);
            articleQuantity.setArticle(article);
            articleQuantities.add(articleQuantity);
        });

        return articleQuantities;
    }

    public void deleteArticleQuantity(ArticleQuantity articleQuantity) {
        repository.delete(mapper.map(articleQuantity, ArticleQuantityEntity.class));
    }

    public void deleteAllArticleQuantitys() {
        repository.deleteAll();
    }
}
