package com.niit.project.userauthentication.service;

import com.niit.project.userauthentication.domain.DatabaseUser;
import com.niit.project.userauthentication.domain.Image;
import com.niit.project.userauthentication.dto.MessageDTO;
import com.niit.project.userauthentication.exception.InvalidCredentialsException;
import com.niit.project.userauthentication.exception.UserEmailAlreadyExistsException;

public interface DatabaseUserService{
    MessageDTO saveUser(DatabaseUser databaseUser) throws UserEmailAlreadyExistsException;

    DatabaseUser getUserFromEmailAndPassword(String userDatabase, String password) throws InvalidCredentialsException;

    Image getImageFromEmail(String email);
}
