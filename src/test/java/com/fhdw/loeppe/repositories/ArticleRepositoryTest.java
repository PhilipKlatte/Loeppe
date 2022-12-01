package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    private UUID entityID;

    @BeforeEach
    public void setUp() {
       ArticleEntity entity = new ArticleEntity();
       entity.setName("Taschentücher");
       entity.setDescription("weiß");
       entity.setPrice(1.10);

       entity = repository.save(entity);
       entityID = entity.getId();
    }

    @Test
    public void saveArticleSuccess(){
        var result = repository.findById(entityID);

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(entityID);
        assertThat(result.get().getName()).isEqualTo("Taschentücher");
        assertThat(result.get().getDescription()).isEqualTo("weiß");
        assertThat(result.get().getPrice()).isEqualTo(1.10);
    }

    @AfterEach
    public void tearDown(){
        repository.deleteById(entityID);
    }
}
