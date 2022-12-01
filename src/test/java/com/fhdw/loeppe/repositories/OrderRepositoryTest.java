package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.entity.OrderEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.repo.OrderRepository;
import com.fhdw.loeppe.util.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp(){
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setAddress("Berlin");
        customerRepository.save(customer);

        ArticleEntity article1 = new ArticleEntity();
        article1.setName("Taschentücher");
        article1.setDescription("weiß");
        article1.setPrice(1.10);
        ArticleEntity article2 = new ArticleEntity();
        article2.setName("Handseife");
        article2.setDescription("Aloe Vera");
        article2.setPrice(2.59);
        List<ArticleEntity> articles = List.of(article1, article2);
        articleRepository.saveAll(articles);

        OrderEntity entity = new OrderEntity();
        entity.setCustomerEntity(customer);
        entity.setArticles(articles);
        entity.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(entity);
    }

    @Test
    public void saveOrderSuccess(){
        var result = orderRepository.findById(1L);

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo( 1L);

        assertThat(result.get().getCustomerEntity().getId()).isEqualTo( 1L);
        assertThat(result.get().getCustomerEntity().getFirstname()).isEqualTo( "John");
        assertThat(result.get().getCustomerEntity().getLastname()).isEqualTo( "Doe");
        assertThat(result.get().getCustomerEntity().getAddress()).isEqualTo( "Berlin");

        assertThat(result.get().getOrderStatus()).isEqualTo(OrderStatus.PAID);

        assertThat(result.get().getArticles().get(0).getId()).isEqualTo( 2L); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben
        assertThat(result.get().getArticles().get(0).getName()).isEqualTo( "Taschentücher");
        assertThat(result.get().getArticles().get(0).getDescription()).isEqualTo( "weiß");
        assertThat(result.get().getArticles().get(0).getPrice()).isEqualTo( 1.10);
        assertThat(result.get().getArticles().get(1).getId()).isEqualTo( 3L); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben
        assertThat(result.get().getArticles().get(1).getName()).isEqualTo( "Handseife");
        assertThat(result.get().getArticles().get(1).getDescription()).isEqualTo( "Aloe Vera");
        assertThat(result.get().getArticles().get(1).getPrice()).isEqualTo( 2.59);
    }

    @AfterEach
    public void tearDown(){
        orderRepository.deleteAll();
        articleRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
