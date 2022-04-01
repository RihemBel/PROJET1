package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.AdressLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdressLivraisonRepository extends JpaRepository<AdressLivraison, UUID> {
}
