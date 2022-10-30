package com.fhdw.loeppe.util;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.dto.Article;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleMapper {
    private final ModelMapper modelMapper;

    public ArticleMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void map(Object entityFrom, Object entityTo) {
        modelMapper.map(entityFrom, entityTo);
    }
    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /*public <D, T> List<D> mapAll(final Collection<T> list, Class<D> outClass) {
        return list.stream()
                .map(entity ->map(entity, outClass))
                .collect(Collectors.toList());
    }*/

    public ArrayList<Article> mapAll(List<ArticleEntity> entities) {
        ArrayList<Article> articles = new ArrayList<>();
        Article article = new Article();

        for (ArticleEntity entity : entities) {
            map(entity, article);
            articles.add(article);
        }

        return articles;
    }
}
