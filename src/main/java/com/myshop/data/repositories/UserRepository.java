package com.myshop.data.repositories;

import com.myshop.data.entities.Role;
import com.myshop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);

    Optional<User> findById(String id);

}
