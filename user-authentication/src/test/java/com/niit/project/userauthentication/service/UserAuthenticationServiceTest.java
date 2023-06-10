package com.niit.project.userauthentication.service;


import com.stackroute.mongoApi.model.Product;
import com.stackroute.mongoApi.model.ProductDescription;
import com.stackroute.mongoApi.respository.IProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAuthenticationServiceTest {

    @Mock
    private IProductRepository iProductRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDescription productDescription;

    @BeforeEach
    void setUp(){
        productDescription = new ProductDescription();
        productDescription.setDescription("testProductDescription");
        product = new Product();
        product.setId("767289");
        product.setName("testProductName");
        product.setCode("6738");
        product.setCost(27.0);
        product.setProductDescription(productDescription);
        product.setStock(13.0);
    }

    @AfterEach
    void tearDown(){
        product = null;
        productDescription = null;
    }

    @Test
    @DisplayName("Test Saving Product")
    void testSavingProduct(){
        when(iProductRepository.save(any())).thenReturn(product);
        Product product1 = productService.saveProduct(product);
        assertEquals(product1.hashCode(), product.hashCode());
        verify(iProductRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Test Getting Data")
    void testGettingData(){
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(iProductRepository.findAll()).thenReturn(productList);
        List<Product> returnedProductList = productService.getAllProducts();
        assertEquals(returnedProductList.size(), productList.size());
        assertEquals(returnedProductList.get(0).hashCode(), productList.get(0).hashCode());
        verify(iProductRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test Getting Product In Stock")
    void testGettingInStockProducts(){
        Product product1 = new Product();
        product1.setId("767290");
        product1.setName("testProduct1Name");
        product1.setCode("6739");
        product1.setCost(28.0);
        product1.setProductDescription(productDescription);
        product1.setStock(8.0);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        when(iProductRepository.findByStockGreaterThan(0.0)).thenReturn(productList);
        List<Product> productList1 = productService.getAllProductsInStock();
        assertEquals(productList1.size(), productList.size());
        assertEquals(productList1.get(0).hashCode(), productList.get(0).hashCode());
        assertEquals(productList1.get(1).hashCode(), productList.get(1).hashCode());
        verify(iProductRepository,times(1)).findByStockGreaterThan(0.0);
    }

    @Test
    @DisplayName("Test Error thrown on invalid Id when mwthod called to update cost")
    void testUpdatingData(){
        assertThrows(NoSuchElementException.class, ()->productService.updateProductCost("0", 0.0));
    }

    @Test
    @DisplayName("Testing getting Product by Id")
    void testGettingProductById(){
        when(iProductRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product product1 = productService.getProduct(product.getId());
        assertEquals(product1.hashCode(), product.hashCode());
        verify(iProductRepository, times(1)).findById(anyString());
    }
}
