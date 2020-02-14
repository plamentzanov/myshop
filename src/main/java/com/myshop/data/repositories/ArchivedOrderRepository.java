package com.myshop.data.repositories;

import com.myshop.data.entities.ArchivedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedOrderRepository extends JpaRepository<ArchivedOrder, String> {
}
