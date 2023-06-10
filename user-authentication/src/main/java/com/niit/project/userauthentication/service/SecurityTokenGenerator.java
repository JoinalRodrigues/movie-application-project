package com.niit.project.userauthentication.service;



import com.niit.project.userauthentication.domain.DatabaseUser;
import com.niit.project.userauthentication.dto.MessageDTO;
import com.niit.project.userauthentication.exception.UserNotEnabledException;

public interface SecurityTokenGenerator {
    MessageDTO generateToken(DatabaseUser databaseUser) throws UserNotEnabledException;
}
