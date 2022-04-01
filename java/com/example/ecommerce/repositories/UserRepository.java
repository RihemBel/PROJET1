package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    String USERS_BY_EMAIL = "usersByEmail";

    String USERS_BY_LOGIN = "usersByLogin";

    Optional<User> findByEmail(String email);

    Optional<User>findById(UUID id);

    Optional<User> findByLogin(String login);

    Page<User> findAllByLoginNot(Pageable pageable, String login);


    @EntityGraph(attributePaths = "roles")
    @Cacheable(cacheNames = USERS_BY_EMAIL)
    Optional<User> findOneWithRolesByEmail(String email);


    @EntityGraph(attributePaths = "ROLES")
    @Cacheable(cacheNames = USERS_BY_LOGIN)
    Optional<User> findOneWithRolesByLogin(String login);

    @Query("select count(u) from User u")
    Long number_users_total();

    @Query("select u from User u")
    List<User> list_users_total();
}
