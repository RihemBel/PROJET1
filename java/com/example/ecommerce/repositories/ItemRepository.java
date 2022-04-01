package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query("select count(i) from Item i")
    Long stat_findTotalItems();

    Optional<Item> findByName(String name);

}
