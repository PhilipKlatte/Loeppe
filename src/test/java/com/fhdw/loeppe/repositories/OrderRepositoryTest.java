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
import java.util.UUID;

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

    private UUID customerEntityID;
    private UUID article1EntityID;
    private UUID article2EntityID;
    private UUID orderEntityID;

    @BeforeEach
    public void setUp(){
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setEmailAdress("johndoe@email.com");
        customer.setPhoneNumber("123456789");
        customer.setCity("Berlin");
        customer.setStreet("Gute Straße 15");
        customer = customerRepository.save(customer);
        customerEntityID = customer.getId();

        ArticleEntity article1 = new ArticleEntity();
        article1.setName("Taschentücher");
        article1.setDescription("weiß");
        article1.setPrice(1.10);
        article1 = articleRepository.save(article1);
        article1EntityID = article1.getId();

        ArticleEntity article2 = new ArticleEntity();
        article2.setName("Handseife");
        article2.setDescription("Aloe Vera");
        article2.setPrice(2.59);
        article2 = articleRepository.save(article2);
        article2EntityID = article2.getId();

        List<ArticleEntity> articles = List.of(article1, article2);

        OrderEntity order = new OrderEntity();
        order.setCustomerEntity(customer);
        order.setArticles(articles);
        order.setOrderStatus(OrderStatus.PAID);
        order = orderRepository.save(order);
        orderEntityID = order.getId();
    }

    @Test
    public void saveOrderSuccess(){
        var result = orderRepository.findById(orderEntityID);

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(orderEntityID);

        assertThat(result.get().getCustomerEntity().getId()).isEqualTo( customerEntityID);
        assertThat(result.get().getCustomerEntity().getFirstname()).isEqualTo( "John");
        assertThat(result.get().getCustomerEntity().getLastname()).isEqualTo( "Doe");
        assertThat(result.get().getCustomerEntity().getCity()).isEqualTo( "Berlin");

        assertThat(result.get().getOrderStatus()).isEqualTo(OrderStatus.PAID);

        assertThat(result.get().getArticles().get(0).getId()).isEqualTo(article1EntityID);
        assertThat(result.get().getArticles().get(0).getName()).isEqualTo("Taschentücher");
        assertThat(result.get().getArticles().get(0).getDescription()).isEqualTo("weiß");
        assertThat(result.get().getArticles().get(0).getPrice()).isEqualTo(1.10);
        assertThat(result.get().getArticles().get(1).getId()).isEqualTo(article2EntityID);
        assertThat(result.get().getArticles().get(1).getName()).isEqualTo("Handseife");
        assertThat(result.get().getArticles().get(1).getDescription()).isEqualTo( "Aloe Vera");
        assertThat(result.get().getArticles().get(1).getPrice()).isEqualTo(2.59);
    }

    @AfterEach
    public void tearDown(){
        orderRepository.deleteById(orderEntityID);
        articleRepository.deleteById(article1EntityID);
        articleRepository.deleteById(article2EntityID);
        customerRepository.deleteById(customerEntityID);
    }
}
