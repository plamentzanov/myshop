package com.myshop.data.repositories;

import com.myshop.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String id);
    List<Product> findAllByCategoryId(String id);
    void deleteById(String id);
}
