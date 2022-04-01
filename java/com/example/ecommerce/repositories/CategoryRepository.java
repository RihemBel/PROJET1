package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.UUID;

@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    //calculate total Categories
    @Query("select count(c) from Category c")
    Long stat_findTotalCategories();

    //find all order by name
    @Query("select c from Category c order by c.name")
    Page<Category> findAllByOrderByName(Pageable pageable);

    Category findByName(String name);

}
