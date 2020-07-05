package org.dell.edu.kube.business.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.dell.edu.kube.business.data.Business;
import org.dell.edu.kube.business.data.BusinessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusinessRepositoryTests {

    @Autowired
    BusinessRepository businessRepository;
    Business business;

    @BeforeEach
    void setUp() {
        business = new Business();
        business.setAddress("Bangalore");
        business.setCategory(1L);
        business.setName("M/S Electronics India");
        business.setOwner("Raghupati");
    }

    @Test
    public void testCreate(){
        business = businessRepository.save(business);
        assertThat(business).toString().contains("Bangalore");
    }

    @Test
    public void testDelete(){
        business = businessRepository.save(business);

        businessRepository.delete(business);
        Optional<Business> temp = businessRepository.findById(business.getId());
        assertThat(temp).isEmpty();

    }

    @Test
    public void testFind(){
        business = businessRepository.save(business);
        assertThat(business).toString().contains("Bangalore");
    }
    @Test
    public void testFindAll(){
        business = businessRepository.save(business);
        assertThat(businessRepository.findAll()).isNotEmpty();
    }
    @Test
    public void testUpdate(){
        business = businessRepository.save(business);
        assertThat(business).toString().contains("Bangalore");
        business.setAddress("Kolkata");
        business = businessRepository.save(business);
        assertThat(business).toString().contains("Kolkata");
    }

}
