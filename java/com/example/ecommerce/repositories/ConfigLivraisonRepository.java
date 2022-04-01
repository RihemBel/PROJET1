package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ConfigLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfigLivraisonRepository extends JpaRepository<ConfigLivraison, UUID> {
    ConfigLivraison findByName(String name);
}
