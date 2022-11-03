package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import com.fhdw.loeppe.repo.CustomerRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Test
    public void saveArticleSuccess(){
        ArticleEntity entity = new ArticleEntity("Taschentücher", "weiß", 1.10);
        repository.save(entity);

        var result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
        assertEquals(result.get().getName(), "Taschentücher");
        assertEquals(result.get().getDescription(), "weiß");
        assertEquals(result.get().getPrice(), 1.10);
    }
}
