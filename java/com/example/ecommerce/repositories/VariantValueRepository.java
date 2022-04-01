package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.VariantValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue,String> {

    VariantValue findByName(String name);
}
