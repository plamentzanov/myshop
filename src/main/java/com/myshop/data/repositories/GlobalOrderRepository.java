package com.myshop.data.repositories;

import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalOrderRepository extends JpaRepository<GlobalOrder, String> {
    @Query( value = "SELECT * FROM global_orders ORDER BY order_date DESC LIMIT 1", nativeQuery = true)
    GlobalOrder findTopByUserOrderByIdDesc(User user);
    GlobalOrder findGlobalOrderById(String id);
}
