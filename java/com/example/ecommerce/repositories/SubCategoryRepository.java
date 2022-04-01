package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory , UUID> {
}
