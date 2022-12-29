package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    ArticleEntity article = new ArticleEntity();


    //@BeforeEach
    public void setUp() {
       article.setName("Taschentücher");
       article.setDescription("weiß");
       article.setPrice(1.10);

       article = repository.save(article);
    }

    //@Test
    public void saveArticleSuccess(){
        var result = repository.findById(article.getId());

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(article.getId());
        assertThat(result.get().getName()).isEqualTo( "Taschentücher");
        assertThat(result.get().getDescription()).isEqualTo( "weiß");
        assertThat(result.get().getPrice()).isEqualTo( 1.10);
    }

    //@AfterEach
    public void tearDown(){
        repository.deleteAll();
    }
}
