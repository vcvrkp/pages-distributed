package org.dell.edu.kube.category.test;


import static org.assertj.core.api.Assertions.assertThat;

import org.dell.edu.kube.category.data.Category;
import org.dell.edu.kube.category.data.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryRepositoryTests {
   @Autowired
   CategoryRepository categoryRepository;
   Category category;

   @BeforeEach
    void setUp(){
       category = new Category();
       category.setDescription("This business sells electronics items");
       category.setName("Consumer Electronics Sales");
       category.setType("Sales");
      categoryRepository.deleteAll();
   }

   @Test
    public void testCreate(){
       category = categoryRepository.save(category);
       assertThat(category).toString().contains("Sales");
   }

   @Test
   public void testDelete(){
      category = categoryRepository.save(category);
      categoryRepository.delete(category);
      Optional<Category> temp = categoryRepository.findById(category.getId());
      assertThat(temp).isEmpty();
   }

   @Test
   public void testFindAll(){
      categoryRepository.save(category);
      assertThat(categoryRepository.findAll()).isNotEmpty();
   }

   @Test
   public void testFind(){
      category = categoryRepository.save(category);
      assertThat(category).toString().contains("Sales");
   }

   @Test
   public void testUpdate(){
      category = categoryRepository.save(category);
      assertThat(category).toString().contains("Sales");
      category.setType("Service");
      category.setDescription("This buisness services electronics");
      category = categoryRepository.save(category);
      assertThat(category).toString().contains("Services");
   }
}
