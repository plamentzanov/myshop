package com.myshop.data.repositories;

import com.myshop.data.entities.GlobalOrder;
import com.myshop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalOrderRepository extends JpaRepository<GlobalOrder, String> {
    GlobalOrder findTopByUserOrderByIdDesc(User user);
}