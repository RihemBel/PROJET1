package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Panier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PanierRepository extends JpaRepository<Panier, UUID> {

    Page<Panier> findAllByItemId(UUID itemId, Pageable pageable);

    List<Panier> findAll();

    //calculate total paniers
    @Query("select count(p) from Panier p")
    Integer findTotalPaniers();

}
