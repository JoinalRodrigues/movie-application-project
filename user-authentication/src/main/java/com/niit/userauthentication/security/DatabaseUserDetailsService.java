package com.niit.userauthentication.security;


import com.niit.userauthentication.domain.DatabaseUser;
import com.niit.userauthentication.domain.Role;
import com.niit.userauthentication.repository.DatabaseUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {

    private final DatabaseUserRepository databaseUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DatabaseUser databaseUser = databaseUserRepository.findByEmail(email);
        if (databaseUser == null)
            throw new UsernameNotFoundException("User not exist");
        return new User(databaseUser.getEmail()
                , databaseUser.getPassword()
                , databaseUser.isEnabled()
                , true
                , true
                ,true
                , getAuthorities(databaseUser.getRoles()));
    }

    public static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }
}