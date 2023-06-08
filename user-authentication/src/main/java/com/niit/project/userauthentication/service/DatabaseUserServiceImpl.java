package com.niit.project.userauthentication.service;

import com.niit.project.userauthentication.domain.DatabaseUser;
import com.niit.project.userauthentication.domain.Image;
import com.niit.project.userauthentication.dto.MessageDTO;
import com.niit.project.userauthentication.exception.InvalidCredentialsException;
import com.niit.project.userauthentication.exception.UserEmailAlreadyExistsException;
import com.niit.project.userauthentication.repository.RoleRepository;
import com.niit.project.userauthentication.repository.DatabaseUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseUserServiceImpl implements DatabaseUserService{

    private final DatabaseUserRepository databaseUserRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    @Transactional
    public MessageDTO saveUser(DatabaseUser databaseUser) throws UserEmailAlreadyExistsException {
        if(this.databaseUserRepository.findByEmail(databaseUser.getEmail()) != null)
            throw new UserEmailAlreadyExistsException();
        DatabaseUser savingDatabaseUser = new DatabaseUser();
        savingDatabaseUser.setEmail(databaseUser.getEmail());
        savingDatabaseUser.setPassword(passwordEncoder.encode(databaseUser.getPassword()));
        savingDatabaseUser.setRoles(List.of(this.roleRepository.findByName("ROLE_USER")));
        savingDatabaseUser.setEnabled(true);
        savingDatabaseUser.setAccountExpiryDate(LocalDate.ofYearDay(9999, 365));
        savingDatabaseUser.setCredentialsExpiryDate(LocalDate.ofYearDay(9999, 365));
        savingDatabaseUser.setAccountNonLocked(true);
        savingDatabaseUser.setImage(databaseUser.getImage());
        MessageDTO messageDTO = new MessageDTO("User " + databaseUserRepository.save(savingDatabaseUser).getEmail() + " registered");
        return messageDTO;
    }

    @Override
    public DatabaseUser getUserFromEmailAndPassword(String email, String password) throws InvalidCredentialsException {
        DatabaseUser databaseUser = databaseUserRepository.findByEmail(email);
        if(databaseUser == null)
            throw new InvalidCredentialsException("User does not exist");
        entityManager.detach(databaseUser);
        if(passwordEncoder.matches(password, databaseUser.getPassword())){
            databaseUser.setPassword(null);
            return databaseUser;
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }

    @Override
    public Image getImageFromEmail(String email) {
        Image image = databaseUserRepository.findOptionalByEmail(email).orElseThrow(InvalidCredentialsException::new).getImage();
        return image;
    }
}
