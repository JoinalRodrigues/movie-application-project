package com.niit.project.userauthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.project.userauthentication.service.DatabaseUserService;
import com.stackroute.mongoApi.exceptions.ProductAlreadyExists;
import com.stackroute.mongoApi.model.Product;
import com.stackroute.mongoApi.model.ProductDescription;
import com.stackroute.mongoApi.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserAuthenticationControllerTest {

    @Mock
    private DatabaseUserService databaseUserService;

    @InjectMocks
    private UserAuthenticationController userAuthenticationController;

    private MockMvc mockMvc;
    private Product product;
    private ProductDescription productDescription;
    private ProductDescription productDescription1;
    private Product product1;
    private List<Product> productList;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setId("767289");
        product.setName("testProductName");
        product.setCode("6738");
        product.setCost(27.0);
        product.setProductDescription(productDescription);
        product.setStock(13.0);
        productDescription = new ProductDescription();
        productDescription.setDescription("testProductDescription");
        product1 = new Product();
        productDescription1 = new ProductDescription();
        productDescription1.setDescription("testproductDescription1");
        product1.setId("6739");
        product1.setCode("7483");
        product1.setName("tesingProduct1");
        product1.setStock(2.0);
        product1.setProductDescription(productDescription1);
        productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    void tearDown(){
        product = null;
        productDescription = null;
        productList = null;
    }
//    @GetMapping("/products")
//    public ResponseEntity<?> getAllProducts()

    @Test
    @DisplayName("Test getting all products")
    void testGettingData() throws Exception{
        when(productService.getAllProducts()).thenReturn(productList);
        mockMvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(jsonToString(productList)));
        verify(productService, times(1)).getAllProducts();
    }

//    @GetMapping("/products/{id}")
//    public ResponseEntity<?> getProduct(@PathVariable("id") String id){
    @Test
    @DisplayName("Test getting product data by id")
    void testGettingSingleProductData() throws Exception{
        when(productService.getProduct(anyString())).thenReturn(product);
        mockMvc.perform(get("/api/v1/products/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andExpect(content().json(jsonToString(product)));
        verify(productService, times(1)).getProduct(anyString());
    }

    @Test
    @DisplayName("Test getting error product not found")
    void testErrorProductNotFound() throws Exception{
        when(productService.getProduct(anyString())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(productService, times(1)).getProduct(anyString());
    }
//        @PostMapping("/products")
//        public ResponseEntity<?> addProduct(@RequestBody Product product){
    @Test
    @DisplayName("Test adding one product")
    void testSavingProduct() throws Exception{
        when(productService.saveProduct(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(product)))
                .andExpect(status().isCreated()).andExpect(content().json(product.getId()));
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    @DisplayName("Test getting error product already exists")
    void testErrorProductAlreadyExists() throws Exception{
        when(productService.saveProduct(any(Product.class))).thenThrow(ProductAlreadyExists.class);
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(product)))
                .andExpect(status().is5xxServerError());
        verify(productService, times(1)).saveProduct(any(Product.class));
    }
//@PatchMapping("/products/{id}")
//public ResponseEntity<?> updateProductCost(@PathVariable String id, @RequestBody Map<String, Double> mapObject){
    @Test
    @DisplayName("Test updating product cost")
    void testUpdatingProductCost() throws Exception{
        Map<String, Double> map = new HashMap<>();
        map.put("cost", product.getCost());
        when(productService.updateProductCost(anyString(), anyDouble())).thenReturn(product.getId());
        mockMvc.perform(patch("/api/v1/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(map)))
                .andExpect(status().isOk())
                .andExpect(content().string(product.getId()));
        verify(productService, times(1)).updateProductCost(anyString(), anyDouble());
    }

    @Test
    @DisplayName("Test getting error on invalid product cost updation")
    void testGettingErrorOnIncorrectDetailsForCostUpdation() throws Exception{
        Map<String, Double> map = new HashMap<>();
        map.put("cost", product.getCost());
        when(productService.updateProductCost(anyString(), anyDouble())).thenReturn(null);
        mockMvc.perform(patch("/api/v1/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(map)))
                .andExpect(status().isInternalServerError());
        verify(productService, times(1)).updateProductCost(anyString(), anyDouble());
    }
//@DeleteMapping("/products/{id}")
//public ResponseEntity<?> deleteProduct(@PathVariable String id){
    @Test
    @DisplayName("Test deleting product data")
    void testDeletingData() throws Exception{
        when(productService.deleteProduct(anyString())).thenReturn(product.getId());
        mockMvc.perform(delete("/api/v1/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(product.getId()));
        verify(productService, times(1)).deleteProduct(anyString());
    }
//@GetMapping("/products/instock")
//public ResponseEntity<?> getProductsInStock(){
    @Test
    @DisplayName("Test getting products in stock")
    void testGettingProductsInStock() throws  Exception{
        when(productService.getAllProductsInStock()).thenReturn(productList);
        mockMvc.perform(get("/api/v1/products/instock"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonToString(productList)));
        verify(productService, times(1)).getAllProductsInStock();
    }

    private String jsonToString(Object o){
        String result;
        try{
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(o);
        }
        catch (JsonProcessingException jsonMappingException){
            result="Invalid object";
        }
        return result;
    }

}
