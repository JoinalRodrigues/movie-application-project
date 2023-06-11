package com.niit.userauthentication.service;

import com.niit.userauthentication.domain.DatabaseUser;
import com.niit.userauthentication.domain.Image;
import com.niit.userauthentication.dto.MessageDTO;
import com.niit.userauthentication.exception.InvalidCredentialsException;
import com.niit.userauthentication.exception.UserEmailAlreadyExistsException;

public interface DatabaseUserService{
    MessageDTO saveUser(DatabaseUser databaseUser) throws UserEmailAlreadyExistsException;

    DatabaseUser getUserFromEmailAndPassword(String userDatabase, String password) throws InvalidCredentialsException;

    Image getImageFromEmail(String email);
}
