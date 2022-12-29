package com.fhdw.loeppe.repositories;

import com.fhdw.loeppe.entity.ArticleEntity;
import com.fhdw.loeppe.entity.CustomerEntity;
import com.fhdw.loeppe.entity.OrderEntity;
import com.fhdw.loeppe.repo.ArticleRepository;
import com.fhdw.loeppe.repo.CustomerRepository;
import com.fhdw.loeppe.repo.OrderRepository;
import com.fhdw.loeppe.util.Country;
import com.fhdw.loeppe.util.OrderStatus;
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

    CustomerEntity customer = new CustomerEntity();
    ArticleEntity article1 = new ArticleEntity();
    ArticleEntity article2 = new ArticleEntity();
    OrderEntity order = new OrderEntity();

    //@BeforeEach
    public void setUp(){
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setCity("Berlin");
        customer.setCountry(Country.ANGOLA);
        customer.setPostalCode("12345");
        customer.setEmailAdress("johndoe@mail.com");
        customer.setPhoneNumber("12345 / 4321");
        customer = customerRepository.save(customer);

        article1.setName("Taschentücher");
        article1.setDescription("weiß");
        article1.setPrice(1.10);
        article1 = articleRepository.save(article1);
        
        article2.setName("Handseife");
        article2.setDescription("Aloe Vera");
        article2.setPrice(2.59);
        article2 = articleRepository.save(article2);

        List<ArticleEntity> articles = List.of(article1, article2);

        order.setCustomerEntity(customer);
        order.setArticles(articles);
        order.setOrderStatus(OrderStatus.PAID);
        order = orderRepository.save(order);
    }

    //@Test
    public void saveOrderSuccess(){
        var result = orderRepository.findById(order.getId());

        assertTrue(result.isPresent());
        assertThat(result.get().getId()).isEqualTo(order.getId());

        assertThat(result.get().getCustomerEntity().getId()).isEqualTo(customer.getId());
        assertThat(result.get().getCustomerEntity().getFirstname()).isEqualTo("John");
        assertThat(result.get().getCustomerEntity().getLastname()).isEqualTo("Doe");
        assertThat(result.get().getCustomerEntity().getCity()).isEqualTo("Berlin");
        assertThat(result.get().getCustomerEntity().getPostalCode()).isEqualTo("12345");
        assertThat(result.get().getCustomerEntity().getCountry()).isEqualTo(Country.ALBANIA);
        assertThat(result.get().getCustomerEntity().getEmailAdress()).isEqualTo("johndoe@mail.com");
        assertThat(result.get().getCustomerEntity().getPhoneNumber()).isEqualTo("12345 / 4321");

        assertThat(result.get().getOrderStatus()).isEqualTo(OrderStatus.PAID);

        assertThat(result.get().getArticles().get(0).getId()).isEqualTo(article1.getId()); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben
        assertThat(result.get().getArticles().get(0).getName()).isEqualTo( "Taschentücher");
        assertThat(result.get().getArticles().get(0).getDescription()).isEqualTo( "weiß");
        assertThat(result.get().getArticles().get(0).getPrice()).isEqualTo( 1.10);
        assertThat(result.get().getArticles().get(1).getId()).isEqualTo(article2.getId()); //TODO: Kapselung der Tests; IDs werden fortlaufend vergeben
        assertThat(result.get().getArticles().get(1).getName()).isEqualTo( "Handseife");
        assertThat(result.get().getArticles().get(1).getDescription()).isEqualTo( "Aloe Vera");
        assertThat(result.get().getArticles().get(1).getPrice()).isEqualTo( 2.59);
    }

    //@AfterEach
    public void tearDown(){
        orderRepository.deleteAll();
        articleRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
