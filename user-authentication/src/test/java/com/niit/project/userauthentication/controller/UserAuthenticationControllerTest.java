package com.niit.project.userauthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.project.userauthentication.domain.DatabaseUser;
import com.niit.project.userauthentication.domain.Image;
import com.niit.project.userauthentication.domain.Role;
import com.niit.project.userauthentication.dto.MessageDTO;
import com.niit.project.userauthentication.service.AdminService;
import com.niit.project.userauthentication.service.DatabaseUserService;
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

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserAuthenticationControllerTest {

    @Mock
    private DatabaseUserService databaseUserService;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private UserAuthenticationController userAuthenticationController;

    private MockMvc mockMvc;
    private DatabaseUser databaseUser;
    @BeforeEach
    void setUp(){
        this.databaseUser = new DatabaseUser();
        this.databaseUser.setId(1L);
        this.databaseUser.setEmail("testingEmail@test.com");
        this.databaseUser.setPassword("testing");
        this.databaseUser.setEnabled(true);
        this.databaseUser.setImage(new Image(new byte[]{1}));
        this.databaseUser.setAccountNonLocked(true);
        this.databaseUser.setCredentialsExpiryDate(LocalDate.ofYearDay(9999, 365));
        this.databaseUser.setAccountExpiryDate(LocalDate.ofYearDay(9999, 365));
        this.databaseUser.setRoles(List.of(new Role("ROLE_USER")));
        mockMvc = MockMvcBuilders.standaloneSetup(userAuthenticationController).build();
    }

    @AfterEach
    void tearDown(){
        databaseUser = null;
    }

//    /api/v1/login
//    /api/v1/admin/login
//    /api/v1/save
//    /api/v1/image
//    /api/v1/admin/users
//    /api/v1/admin/users
//    /api/v1/admin/roles

    @Test
    @DisplayName("Test login")
    void testGettingData() throws Exception{
        when(this.databaseUserService.saveUser(this.databaseUser)).thenReturn(new MessageDTO("User " + this.databaseUser.getEmail() + " registered"));
        mockMvc.perform(get("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(jsonToString(new MessageDTO("User " + this.databaseUser.getEmail() + " registered"))));
        verify(this.databaseUserService, times(1)).saveUser(this.databaseUser);
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
