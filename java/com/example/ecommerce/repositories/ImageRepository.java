package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Image;
import com.example.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> deleteById(Long id);

    Optional<Image> findById(Long id);
}
