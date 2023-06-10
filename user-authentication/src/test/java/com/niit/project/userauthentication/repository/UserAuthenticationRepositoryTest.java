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
        this.databaseUser = new DatabaseUser();
        this.databaseUser.setEmail("testingEmail@test.com");
        this.databaseUser.setPassword("testing");
        this.databaseUser.setEnabled(true);
        this.databaseUser.setImage(new Image(new byte[]{1}));
        this.databaseUser.setAccountNonLocked(true);
        this.databaseUser.setCredentialsExpiryDate(LocalDate.ofYearDay(9999, 365));
        this.databaseUser.setAccountExpiryDate(LocalDate.ofYearDay(9999, 365));
        this.databaseUser.setRoles(List.of(new Role("ROLE_USER")));
    }

    @AfterEach
    void tearDown(){
        if(this.databaseUser.getId() != null && this.databaseUser.getId() != 0L)
            this.databaseUserRepository.deleteById(this.databaseUser.getId());
    }

    @Test
    @DisplayName("Testing saving user")
    void testSavingUser(){
        this.databaseUser = this.databaseUserRepository.save(this.databaseUser);
        DatabaseUser databaseUser1 = this.databaseUserRepository.findById(this.databaseUser.getId()).orElse(null);
        assertNotNull(databaseUser1);
        assertEquals(this.databaseUser, databaseUser1);
    }

    @Test
    @DisplayName("Test getting user by email")
    void testGettingUserByEmail(){
        this.databaseUser = this.databaseUserRepository.save(this.databaseUser);
        DatabaseUser databaseUser1 = this.databaseUserRepository.findByEmail(this.databaseUser.getEmail());
        assertNotNull(databaseUser1);
        assertEquals(this.databaseUser, databaseUser1);
    }

    @Test
    @DisplayName("Testing user not saved when image is not provided")
    void testUserNotSavedWhenNoImage(){
        this.databaseUser.setImage(null);
        assertThrows(Exception.class, () -> this.databaseUserRepository.save(this.databaseUser));
    }

    @Test
    @DisplayName("Testing email id is unique")
    void testEmailIdIsUnique(){
        DatabaseUser databaseUser1 = new DatabaseUser();
        databaseUser1.setEmail(this.databaseUser.getEmail());
        databaseUser1.setPassword(this.databaseUser.getPassword());
        databaseUser1.setEnabled(this.databaseUser.isEnabled());
        databaseUser1.setImage(this.databaseUser.getImage());
        databaseUser1.setAccountNonLocked(this.databaseUser.isAccountNonLocked());
        databaseUser1.setCredentialsExpiryDate(this.databaseUser.getCredentialsExpiryDate());
        databaseUser1.setAccountExpiryDate(this.databaseUser.getAccountExpiryDate());
        databaseUser1.setRoles(this.databaseUser.getRoles());
        this.databaseUser = this.databaseUserRepository.save(this.databaseUser);
        DatabaseUser finalDatabaseUser = databaseUser1;
        assertThrows(Exception.class, () -> this.databaseUserRepository.save(finalDatabaseUser));
        databaseUser1 = this.databaseUserRepository.findOptionalByEmail(databaseUser1.getEmail()).orElse(null);
        if(databaseUser1 != null)
            this.databaseUserRepository.deleteById(databaseUser1.getId());
    }

}
