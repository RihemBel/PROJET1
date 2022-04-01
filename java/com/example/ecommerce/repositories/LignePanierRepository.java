package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.LignePanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LignePanierRepository extends JpaRepository<LignePanier, UUID> {

    //calculate total lignepaniers
    @Query("select count(lp) from LignePanier lp")
    Integer findTotalLignePaniers();}
