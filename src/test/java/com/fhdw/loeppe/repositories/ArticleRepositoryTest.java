package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @BeforeEach
    public void setUp() {
       /* ArticleEntity entity = new ArticleEntity("Taschentücher", "weiß", 1.10);
        repository.save(entity);*/
    }

    @Test
    public void saveArticleSuccess(){
        var result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo( 1L);
        assertThat(result.get().getName()).isEqualTo( "Taschentücher");
        assertThat(result.get().getDescription()).isEqualTo( "weiß");
        assertThat(result.get().getPrice()).isEqualTo( 1.10);
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
    }
}
