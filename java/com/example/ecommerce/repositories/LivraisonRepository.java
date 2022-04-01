package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Livraison;
import com.example.ecommerce.entities.Mark;
import com.example.ecommerce.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, UUID> {

    List<Livraison> findAll();

    Livraison findByName(String name);

    @Query("select count(l) from Livraison l ")
    Long stat_findTotalLivraison();

}
