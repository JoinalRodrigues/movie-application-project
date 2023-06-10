package com.niit.project.userauthentication.repository;


import com.niit.project.userauthentication.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
