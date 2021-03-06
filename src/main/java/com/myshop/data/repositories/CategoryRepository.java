package com.myshop.data.repositories;

import com.myshop.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
    Optional<Category> findById(String id);
    void deleteById(String id);
}
