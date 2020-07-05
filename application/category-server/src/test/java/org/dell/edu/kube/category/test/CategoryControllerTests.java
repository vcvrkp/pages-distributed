package org.dell.edu.kube.category.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.dell.edu.kube.category.CategoryController;
import org.dell.edu.kube.category.data.Category;
import org.dell.edu.kube.category.data.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    CategoryController categoryController;
    Category category;
    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp(){
        category = new Category();
        category.setDescription("This business sells electronics items");
        category.setName("Consumer Electronics Sales");
        category.setType("Sales");
        categoryRepository.deleteAll();
    }

    @Test
    public void testFind(){
          String url = "http://localhost:"+port+"/category";
          String result = restTemplate.getForObject(url+"/10",String.class);
          assertThat(result).contains("No Category Available");

          ResponseEntity responseEntity = restTemplate.postForEntity(url,category,Category.class);
          assertThat(responseEntity.getBody()).toString().contains("Sales");
    }

    @Test
    public void testAll(){
        String url = "http://localhost:"+port+"/category";
        restTemplate.postForEntity(url,category,Category.class);
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getBody()).toString().contains("Sales");

    }
    @Test
    public void testCreate(){
        String url = "http://localhost:"+port+"/category";
        ResponseEntity<Category> cat = restTemplate.postForEntity(url,category,Category.class);
        ResponseEntity<Category> entity = restTemplate.getForEntity(url+"/{id}",Category.class,cat.getBody().getId());
        assertThat(entity.getBody().getType()).contains("Sales");
    }
    @Test
    public void testUpdate(){
        String url = "http://localhost:"+port+"/category";
        ResponseEntity<Category> cat=restTemplate.postForEntity(url,category,Category.class);
        ResponseEntity<Category> responseEntity = restTemplate.getForEntity(url+"/{id}",Category.class,cat.getBody().getId());
        category = responseEntity.getBody();
        assertThat(category.getType()).contains("Sales");
        category.setType("Services");
        restTemplate.put(url+"/{id}",category,category.getId());
        responseEntity = restTemplate.getForEntity(url+"/{id}",Category.class,category.getId());
        assertThat(responseEntity.getBody().getType()).contains("Services");
    }
    @Test
    public void testRemove(){

    }
}
