package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Role;
import com.example.ecommerce.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    //Optional<Role> findByRoleName(String role);

    Optional<Role> findByRoleName(RoleName roleName);
}
