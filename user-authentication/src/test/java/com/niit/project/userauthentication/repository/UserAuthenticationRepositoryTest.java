package com.niit.project.userauthentication.repository;

import com.niit.project.userauthentication.domain.DatabaseUser;
import com.niit.project.userauthentication.domain.Image;
import com.niit.project.userauthentication.domain.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = SpringExtension.class)
@DataMongoTest
public class UserAuthenticationRepositoryTest {

    @Autowired
    private DatabaseUserRepository databaseUserRepository;
    private DatabaseUser databaseUser;

    @BeforeEach
    void setUp(){
        databaseUser = new DatabaseUser();
        databaseUser.setEmail("testingEmail@test.com");
        databaseUser.setPassword("testing");
        databaseUser.setEnabled(true);
        databaseUser.setImage(new Image(new byte[]{1}));
        databaseUser.setAccountNonLocked(true);
        databaseUser.setCredentialsExpiryDate(LocalDate.ofYearDay(9999, 365));
        databaseUser.setAccountExpiryDate(LocalDate.ofYearDay(9999, 365));
        databaseUser.setRoles(List.of(new Role("ROLE_USER")));
    }

    @AfterEach
    void tearDown(){
        databaseUserRepository.deleteById(databaseUser.getId());
    }

    @Test
    @DisplayName("Testing saving user")
    void testsavingProductData(){
        databaseUser = databaseUserRepository.save(databaseUser);
        DatabaseUser databaseUser1 = databaseUserRepository.findById(databaseUser.getId()).orElse(null);
        assertNotNull(databaseUser1);
        assertEquals(databaseUser, databaseUser1);
    }

    @Test
    @DisplayName("Testing saving user")
    void testDeletingProductData(){
        String id = iProductRepository.save(product).getId();
        Product product1 = iProductRepository.findById(id).orElse(null);
        assertNotNull(product1);
        assertEquals(product1.hashCode(), product.hashCode());
        iProductRepository.deleteProductById(product.getId());
        product1 = iProductRepository.findById(id).orElse(null);
        assertNull(product1);
    }

    @Test
    @DisplayName("Testing updating Cost When Id is provided")
    void testUpdateProductCost(){
        Double cost = iProductRepository.save(product).getCost();
        assertEquals(cost, product.getCost());
        iProductRepository.findAndUpdateAllById(product.getId(), 89.0);
        Product product1 = iProductRepository.findById(product.getId()).orElse(null);
        assertNotNull(product1);
        assertEquals(89.0, product1.getCost());
    }

    @Test
    @DisplayName("Testing finding products in stock method")
    void testGettingProductsInStock(){
        Product product1 = new Product();
        ProductDescription productDescription1 = new ProductDescription();
        productDescription1.setDescription("testproductDescription1");
        product1.setId("6739");
        product1.setCode("7483");
        product1.setName("tesingProduct1");
        product1.setStock(2.0);
        product1.setProductDescription(productDescription1);
        iProductRepository.save(product);
        iProductRepository.save(product1);
        List<Product> products = iProductRepository.findByStockGreaterThan(4.0);
        assertEquals(products.size(), 1);
        assertEquals(products.get(0).hashCode(), product.hashCode());
        products = iProductRepository.findByStockGreaterThan(1.0);
        assertEquals(products.size(), 2);
        assertEquals(products.get(0).hashCode(), product.hashCode());
        assertEquals(products.get(1).hashCode(), product1.hashCode());
    }

    @Test
    @DisplayName("Failing to get data")
    void failGettingData(){
        Product product1 = iProductRepository.findById(product.getId()).orElse(null);
        assertNull(product1);
        assertThrows(NullPointerException.class, () -> product1.getId());
    }

}
