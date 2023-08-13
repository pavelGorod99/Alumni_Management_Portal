package com.example.Alumni_Management_Portal.repositories;

import com.example.Alumni_Management_Portal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleIgnoreCase(String role);

}
