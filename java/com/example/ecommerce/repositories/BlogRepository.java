package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Blog;
import com.example.ecommerce.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {

    Optional<Image> findById(Long id);
}
