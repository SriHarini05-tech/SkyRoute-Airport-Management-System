package com.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airport.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}