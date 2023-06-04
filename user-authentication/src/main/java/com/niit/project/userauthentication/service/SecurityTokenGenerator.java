package com.niit.project.userauthentication.service;



import com.niit.project.userauthentication.domain.DatabaseUser;
import com.niit.project.userauthentication.dto.MessageDTO;

public interface SecurityTokenGenerator {
    MessageDTO generateToken(DatabaseUser databaseUser);
}
