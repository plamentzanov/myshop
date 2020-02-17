package com.myshop.data.repositories;

import com.myshop.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByUserUsername(String username);
    void deleteAllByUserUsername(String username);
}
